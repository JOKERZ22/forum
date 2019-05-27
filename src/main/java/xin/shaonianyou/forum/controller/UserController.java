package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.PostComment;
import xin.shaonianyou.forum.service.CommentService;
import xin.shaonianyou.forum.service.PostService;
import xin.shaonianyou.forum.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    //注册用户
    @PostMapping("/reg")
    @ResponseBody
    public Map<String, String> reg(User pageuser) {

        return userService.insertUser(pageuser);
    }

    //用户登录--通过邮箱
    @PostMapping("/userlogin")
    @ResponseBody
    public Map<String, String> login(User pageuser, HttpSession session) {

        return userService.selectUserByEmail(pageuser, session);
    }

    //用户登出
    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        if (!(session.getAttribute("usersession") == null)) {
            session.removeAttribute("usersession");
        }
        return "redirect:/";
    }

    //用户主页
    @RequestMapping(path = "/userhome/{userid}", method = RequestMethod.GET)
    public String userhome(Model model, @PathVariable long userid) {

        Post post = new Post();

        post.setUser_id(userid);

        //查询最近提问列表
        List<Post> questionPostList = postService.selectByPost(post);
        //查询最近回复列表
        List<PostComment> answerCommentList = commentService.selectByUserId(userid);
        //根据用户id查询用户
        User selectUser = userService.selectById(userid);

        model.addAttribute("questionPostList", questionPostList);
        model.addAttribute("answerCommentList", answerCommentList);
        model.addAttribute("selectUser", selectUser);

        return "user/home";
    }

    //用户中心
    @RequestMapping(path = "/userindex", method = RequestMethod.GET)
    public String userindex(Model model, HttpSession session) {

        Post post = new Post();

        User usersession = (User) session.getAttribute("usersession");
        post.setUser_id(usersession.getId());

        //查询最近发表帖子列表
        List<Post> currentPostList = postService.selectByPost(post);
        //根据用户id查询其发帖总数
        long postcount = postService.selsectCountByUserId(usersession.getId());

        model.addAttribute("currentPostList", currentPostList);
        model.addAttribute("postcount", postcount);
        return "user/index";
    }

    //修改用户
    @PostMapping("/update")
    @ResponseBody
    public Map<String, String> update(User newuser, HttpSession session) {

        //获取当前用户，得到id
        User usersession = (User) session.getAttribute("usersession");

        //将当前用户id赋予前台传入user对象
        newuser.setId(usersession.getId());

        Map<String, String> resultMap = userService.updateSection(newuser);

        //修改成功则清空session
        if (resultMap.get("message").equals("1")) {
            User updateuser = userService.selectById(usersession.getId());
            session.setAttribute("usersession",updateuser);
        }

        return resultMap;
    }

    //修改用户密码
    @PostMapping("/repass")
    @ResponseBody
    public Map<String, String> repass(String currentpass, String newpass, HttpSession session) {

        //获取当前用户，得到密码，前台传入输入密码和新密码
        User usersession = (User) session.getAttribute("usersession");

        Map<String, String> resultMap = userService.repass(currentpass, newpass, usersession);

        //修改成功则清空session
        if (resultMap.get("message").equals("1")) {
            session.invalidate();
        }

        return resultMap;
    }

    //修改用户头像
    @PostMapping("/avator")
    @ResponseBody
    public Map<String, String> updateAvator(String avatar,HttpSession session){

        Map<String, String> resultMap = new HashMap<String, String>();
        User usersession =(User) session.getAttribute("usersession");
        if(avatar == null){
            resultMap.put("message","上传失败！");
        }else {
            long l = userService.updateAvator(avatar, usersession.getId());
            if(l<1){
                resultMap.put("message","修改失败！");
            }else {
                resultMap.put("message","1");
                User newuser = userService.selectById(usersession.getId());
                session.setAttribute("usersession",newuser);
            }
        }
        return resultMap;
    }
}
