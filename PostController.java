package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xin.shaonianyou.forum.entity.Category;
import xin.shaonianyou.forum.entity.Module;
import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.CommentUser;
import xin.shaonianyou.forum.entity.vo.PostSearch;
import xin.shaonianyou.forum.entity.vo.PostUser;
import xin.shaonianyou.forum.service.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ModuleService moduleService;

    //发帖
    @PostMapping("addpost")
    @ResponseBody
    public Map<String, String> addpost(Post post, HttpSession session) {

        User user = (User) session.getAttribute("usersession");

        return postService.insert(post, user);
    }

    //按版块查询帖子
    @RequestMapping(value = "/module/{moduleid}", method = RequestMethod.GET)
    public String selectByModule(Model model, @PathVariable long moduleid) {
        //按版块查询帖子
        List<PostUser> indexPostUserList = postService.selectByModule(moduleid);
        //本周热议
        List<Post> weekHotPostList = this.selectHotPostByWeek();

        model.addAttribute("indexPostUserList", indexPostUserList);
        model.addAttribute("weekHotPostList", weekHotPostList);
        return "jie/index";
    }

    //按类别查询帖子
    @RequestMapping(value = "/category/{categoryid}", method = RequestMethod.GET)
    public String selectByCategory(Model model, @PathVariable long categoryid) {
        //按类别查询帖子
        List<Post> postListByCategory = postService.selectByCategory(categoryid);
        //本周热议
        List<Post> weekHotPostList = this.selectHotPostByWeek();

        model.addAttribute("postListByCategory", postListByCategory);
        model.addAttribute("weekHotPostList", weekHotPostList);
        return "jie/index";
    }

    //本周热议
    public List<Post> selectHotPostByWeek() {
        return postService.selectHotPostByWeek();
    }

    //帖子详情页
    @RequestMapping(value = "/postdetail/{postid}", method = RequestMethod.GET)
    public String postdetail(Model model, @PathVariable long postid) {

        //帖子详情
        Post currentpost = postService.selectById(postid);
        //根据帖子类别id查询category，获得类别名称
        Category currentcategory = categoryService.selectCategoryById(currentpost.getCategory_id());
        //根据帖子版块id查询module，获取版块名称
        Module currentmodule = moduleService.selectModuleById(currentpost.getModule_id());
        //发帖人详情
        User postuser = userService.selectById(currentpost.getUser_id());
        //回复列表
        List<CommentUser> commentUserList = commentService.selectCommentListByPostId(postid);
        //本周热议
        List<Post> weekHotPostList = this.selectHotPostByWeek();

        model.addAttribute("weekHotPostList", weekHotPostList);
        model.addAttribute("currentpost", currentpost);
        model.addAttribute("currentcategory", currentcategory);
        model.addAttribute("currentmodule", currentmodule);
        model.addAttribute("postuser", postuser);
        model.addAttribute("commentUserList", commentUserList);
        model.addAttribute("commentUserList", commentUserList);
        return "jie/detail";
    }

    //点赞--评论
    @PostMapping("/zan")
    @ResponseBody
    public Map<String, String> commentZan(long commentid, HttpSession session) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        User usersession = (User) session.getAttribute("usersession");
        if (usersession == null) {
            resultMap.put("message", "未登录，请先登录！");
        } else if (usersession.getId() == commentService.selectCommentById(commentid).getUser_id()) {
            resultMap.put("message", "您不能为自己点赞！");
        } else {
            resultMap = commentService.commentIncrease(commentid);
        }

        return resultMap;
    }

    //多条件查询帖子
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ModelAndView searchPost(Long tmodule, Long tcategory, Long tlatest, Long thot) {

        ModelAndView mv = new ModelAndView();

        //设置查询条件及排序方式
        PostSearch postSearch = new PostSearch();
        if(tmodule == null){
            postSearch.setModuleid(0);
        }else if (tmodule > 0) {
            postSearch.setModuleid(tmodule);
        }
        if(tcategory == null){
            postSearch.setCategoryid(0);
        }else if (tcategory > 0) {
            postSearch.setCategoryid(tcategory);
        }
        if(tlatest == null){
            postSearch.setSortby(2);
        }else if (tlatest > 0) {
            postSearch.setSortby(1);
        }
        if(thot == null){
            postSearch.setSortby(1);
        }else if(thot >0){
            postSearch.setSortby(2);
        }else {
            postSearch.setSortby(1);
        }
        //查出所需列表
        List<PostUser> indexPostUserList = postService.postSearch(postSearch);

        mv.addObject("indexPostUserList",indexPostUserList);

        //将页面传递来的参数带入下个页面
        mv.addObject("tmodule",tmodule);
        mv.addObject("tcategory",tcategory);
        mv.addObject("tlatest",tlatest);
        mv.addObject("thot",thot);

        mv.setViewName("jie/index");
        return mv;
    }
}
