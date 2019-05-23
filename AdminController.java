package xin.shaonianyou.forum.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xin.shaonianyou.forum.entity.Category;
import xin.shaonianyou.forum.entity.Module;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.service.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    //跳转登录页面
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String adminlogin() {
        return "admin/login";
    }

    //跳转后台首页
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String adminindex(Model model) {

        //首页嵌套welcome页，查出welcome页所需数据
        this.welcome(model);
        return "admin/index";
    }

    //用户登录--通过用户名
    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(User pageuser, HttpSession session) {

        return userService.selectUserByUserName(pageuser, session);
    }

    //用户登出
    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        if (!(session.getAttribute("adminusersession") == null)) {
            session.removeAttribute("adminusersession");
        }
        return "redirect:index";
    }

    //用户切换
    @RequestMapping("/switch")
    public String switchuser(HttpSession session) {

        if (!(session.getAttribute("adminusersession") == null)) {
            session.removeAttribute("adminusersession");
        }
        return "admin/login";
    }

    //欢迎页
    @RequestMapping("/welcome")
    public void welcome(Model model) {

        //查询各项总数
        long usercount = userService.userCount();
        long postcount = postService.postCount();
        long modulecount = moduleService.moduleCount();
        long commentcount = commentService.commentCount();
        long categorycount = categoryService.categoryCount();

        model.addAttribute("usercount", usercount);
        model.addAttribute("postcount", postcount);
        model.addAttribute("modulecount", modulecount);
        model.addAttribute("commentcount", commentcount);
        model.addAttribute("categorycount", categorycount);

    }

    //用户列表
    @RequestMapping("/userlist")
    public String userList(Model model, Integer page, Integer rows) {

        //设置分页
        PageHelper.startPage(page == null ? 1 : page, rows == null ? 10 : rows);

        List<User> userList = userService.selectAll();
        PageInfo pageInfo = new PageInfo(userList);
        long userCount = pageInfo.getTotal();

        model.addAttribute("userList", userList);
        model.addAttribute("userCount", userCount);

        return "admin/user-list";
    }

    //分类
    @RequestMapping("/category")
    public String categoryList(Model model) {
        List<Category> categoryList = categoryService.selectAll();
        long categoryCount = categoryService.categoryCount();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("categoryCount", categoryCount);
        return "admin/cate";
    }

    //模块
    @RequestMapping("/module")
    public String moduleList(Model model) {
        List<Module> moduleList = moduleService.selectAll();
        long moduleCount = moduleService.moduleCount();
        model.addAttribute("moduleList", moduleList);
        model.addAttribute("moduleCount", moduleCount);
        return "admin/module";
    }

    //根据注册时间，用户名查询用户
    @PostMapping("/search")
    public String selectUserBySection(String start, String end, String username, Model model) {

        //设置分页
        PageHelper.startPage(1, 10);
        List<User> userList = userService.selectUserBySection(start, end, username);

        PageInfo pageInfo = new PageInfo(userList);
        long userCount = pageInfo.getTotal();

        model.addAttribute("userList", userList);
        model.addAttribute("userCount", userCount);
        return "admin/user-list";
    }

    //添加模块
    @PostMapping("/addmodule")
    @ResponseBody
    public Map<String, String> addModule(String displayname, HttpSession session) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        Module module = new Module();

        User adminusersession = (User) session.getAttribute("adminusersession");

        if (adminusersession == null) {
            resultMap.put("message", "请先登录");
        } else {
            module.setDisplay_name(displayname);
            module.setName(displayname);
            module.setUsername(adminusersession.getName());
            resultMap = moduleService.addModule(module);
        }

        return resultMap;
    }

    //添加分类
    @PostMapping("/addcategory")
    @ResponseBody
    public Map<String, String> addCategory(String displayname, HttpSession session) {
        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        Category category = new Category();

        User adminusersession = (User) session.getAttribute("adminusersession");

        if (adminusersession == null) {
            resultMap.put("message", "请先登录");
        } else {
            category.setDisplay_name(displayname);
            category.setName(displayname);
            category.setUsername(adminusersession.getName());
            resultMap = categoryService.addCategory(category);
        }
        return resultMap;
    }

    //删除分类
    @PostMapping("/delcategory")
    @ResponseBody
    public Map<String, String> delCategory(long categoryid, HttpSession session) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        User adminusersession = (User) session.getAttribute("adminusersession");

        if (adminusersession == null) {
            resultMap.put("message", "请先登录");
        } else {
            resultMap = categoryService.delCategory(categoryid);
        }
        return resultMap;
    }

    //删除模块
    @PostMapping("/delmodule")
    @ResponseBody
    public Map<String, String> delNodule(long moduleid, HttpSession session) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        User adminusersession = (User) session.getAttribute("adminusersession");

        if (adminusersession == null) {
            resultMap.put("message", "请先登录！");
        } else {
            resultMap = moduleService.delModule(moduleid);
        }
        return resultMap;
    }

    //跳转模块编辑
    @RequestMapping("/toeditmodule/{id}")
    public String toEditModule(@PathVariable long id, Model model) {
        model.addAttribute("moduleid", id);
        return "admin/module-edit";
    }

    //模块编辑
    @PostMapping("/editmodule")
    @ResponseBody
    public Map<String, String> editModule(Module module, HttpSession session) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        User adminusersession = (User) session.getAttribute("adminusersession");

        if (adminusersession == null) {
            resultMap.put("message", "请先登录！");
        } else {
            resultMap = moduleService.updateModule(module);
        }
        return resultMap;
    }

    //跳转分类编辑
    @RequestMapping("/toeditcategory/{id}")
    public String toEditCategory(@PathVariable long id, Model model) {
        model.addAttribute("categoryid", id);
        return "admin/category-edit";
    }

    //分类编辑
    @PostMapping("/editcategory")
    @ResponseBody
    public Map<String, String> editCategory(Category category, HttpSession session) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        User adminusersession = (User) session.getAttribute("adminusersession");

        if (adminusersession == null) {
            resultMap.put("message", "请先登录！");
        } else {
            resultMap = categoryService.updateCategory(category);
        }
        return resultMap;
    }

    //修改用户状态
    @PostMapping("/userstatus")
    @ResponseBody
    public Map<String, String> updateUserStatus(long userid) {
        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long status = 0;

        User targetuser = userService.selectById(userid);
        if (targetuser.getStatus() == 0) {
            status = 1;
        } else {
            status = 0;
        }

        long l = userService.updateUserStatus(status, userid);
        if (l < 1) {
            resultMap.put("message", "修改失败");
        } else {
            resultMap.put("message", "1");
        }
        return resultMap;
    }

    //删除用户
    @PostMapping("/userdelete")
    @ResponseBody
    public Map<String, String> deleteUser(long userid) {
        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long l = userService.deleteUser(userid);
        if (l < 1) {
            resultMap.put("message", "修改失败");
        } else {
            resultMap.put("message", "1");
        }
        return resultMap;
    }

    //修改用户是否为管理员
    @PostMapping("/useradmin")
    @ResponseBody
    public Map<String, String> updateAdmin(long userid) {
        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();
        long status = 0;

        User targetuser = userService.selectById(userid);
        if (targetuser.getIs_administrator() == 0) {
            status = 1;
        } else {
            status = 0;
        }
        long l = userService.updateUserAdmin(status, userid);
        if (l < 1) {
            resultMap.put("message", "修改失败");
        } else {
            resultMap.put("message", "1");
        }
        return resultMap;
    }
}
