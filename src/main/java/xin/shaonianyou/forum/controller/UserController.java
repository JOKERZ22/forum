package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //注册用户
    @PostMapping("/reg")
    @ResponseBody
    public Map<String, String> reg(User pageuser) {

        return userService.insertUser(pageuser);
    }

    //用户登陆--通过邮箱
    @PostMapping("/userlogin")
    @ResponseBody
    public Map<String, String> login(User pageuser, HttpSession session) {

        return userService.selectUserByEmail(pageuser, session);
    }

    //用户登出
    @RequestMapping("/logout")
    public String logout(User user, HttpSession session) {

        if (!(session.getAttribute("usersession") == null)) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
