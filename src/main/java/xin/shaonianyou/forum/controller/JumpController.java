package xin.shaonianyou.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xin.shaonianyou.forum.entity.User;

@Controller
public class JumpController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "user/login";
    }

    @RequestMapping(path = "/reg", method = RequestMethod.GET)
    public String reg(Model model){
        model.addAttribute("user",new User());
        return "user/reg";
    }

    @RequestMapping(path = "/userindex", method = RequestMethod.GET)
    public String userindex(){
        return "user/index";
    }

    @RequestMapping(path = "/postadd", method = RequestMethod.GET)
    public String postadd(){
        return "jie/add";
    }

    @RequestMapping(path = "/forget", method = RequestMethod.GET)
    public String forget(){
        return "user/forget";
    }



}
