package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xin.shaonianyou.forum.entity.*;
import xin.shaonianyou.forum.entity.vo.CommentUser;
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

    //按模块查询帖子
    @RequestMapping(value = "/module/{moduleid}", method = RequestMethod.GET)
    public String selectByModule(Model model, @PathVariable long moduleid) {
        //按模块查询帖子
        List<Post> postListByModule = postService.selectByModule(moduleid);
        //本周热议
        List<Post> weekHotPostList = this.selectHotPostByWeek();

        model.addAttribute("postListByModule", postListByModule);
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
        //根据帖子模块id查询module，获取模块名称
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

    //评论删除
}
