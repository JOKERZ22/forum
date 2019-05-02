package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    //发帖
    @PostMapping("addpost")
    @ResponseBody
    public Map<String, String> addpost(Post post, HttpSession session) {
        return postService.insert(post, session);
    }

    //按模块查询帖子
    @RequestMapping(value = "/module/{moduleid}", method = RequestMethod.GET)
    public String selectByModule(Model model, @PathVariable int moduleid) {
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
    public String selectByCategory(Model model, @PathVariable int categoryid) {
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
}
