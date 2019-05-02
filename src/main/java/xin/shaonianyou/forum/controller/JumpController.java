package xin.shaonianyou.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
    /*
    * 页面跳转控制
    * */
@Controller
public class JumpController {

    @RequestMapping(path = "/tologin", method = RequestMethod.GET)
    public String login() {
        return "user/login";
    }

    @RequestMapping(path = "/toreg", method = RequestMethod.GET)
    public String reg(Model model) {
        return "user/reg";
    }

    @RequestMapping(path = "/touserindex", method = RequestMethod.GET)
    public String userindex() {
        return "user/index";
    }

    @RequestMapping(path = "/touserset", method = RequestMethod.GET)
    public String userset() {
        return "user/set";
    }

    @RequestMapping(path = "/tousermessage", method = RequestMethod.GET)
    public String usermessage() {
        return "user/message";
    }

    @RequestMapping(path = "/touserhome", method = RequestMethod.GET)
    public String userhome() {
        return "user/home";
    }

    @RequestMapping(path = "/topostadd", method = RequestMethod.GET)
    public String postadd() {
        return "jie/add";
    }

    @RequestMapping(path = "/toforget", method = RequestMethod.GET)
    public String forget() {
        return "user/forget";
    }

    @RequestMapping(path = "/todetail", method = RequestMethod.GET)
    public String detail() {
        return "jie/detail";
    }

    @RequestMapping(path = "/tojieindex", method = RequestMethod.GET)
    public String jieindex() {
        return "jie/index";
    }

}
