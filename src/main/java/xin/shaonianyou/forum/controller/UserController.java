package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //注册用户
    @RequestMapping(value="/reg", method= RequestMethod.POST)
    public String insert(User user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
       userService.insert(user);
        return "redirect:index";
    }


}
