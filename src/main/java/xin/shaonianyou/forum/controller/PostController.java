package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.CommentUser;
import xin.shaonianyou.forum.service.CommentService;
import xin.shaonianyou.forum.service.PostService;
import xin.shaonianyou.forum.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private  UserService userService;
    @Autowired
    private CommentService commentService;

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
    @RequestMapping(value = "/postdetail/{postid}",method = RequestMethod.GET)
    public String  postdetail(Model model,@PathVariable long postid){

        //帖子详情
        Post currentpost = postService.selectById(postid);
        //发帖人详情
        User postuser = userService.selectById(currentpost.getUser_id());
        //回复列表
        List<CommentUser> commentUserList = commentService.selectCommentListByPostId(postid);
        //本周热议
        List<Post> weekHotPostList = this.selectHotPostByWeek();

        model.addAttribute("weekHotPostList", weekHotPostList);
        model.addAttribute("currentpost",currentpost);
        model.addAttribute("postuser",postuser);
        model.addAttribute("commentUserList",commentUserList);
        model.addAttribute("commentUserList",commentUserList);
        return "jie/detail";
    }
}
