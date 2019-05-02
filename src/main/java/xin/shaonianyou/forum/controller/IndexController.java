package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xin.shaonianyou.forum.entity.Category;
import xin.shaonianyou.forum.entity.Module;
import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.entity.vo.WeekUser;
import xin.shaonianyou.forum.service.CategoryService;
import xin.shaonianyou.forum.service.ModuleService;
import xin.shaonianyou.forum.service.PostService;
import xin.shaonianyou.forum.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
    /*
    * 首页控制
    * */
@Controller
public class IndexController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView  index(HttpSession session) {

        ModelAndView mv = new ModelAndView();

        List<Module> moduleList = moduleService.selectAll();
        List<Category> categoryList = categoryService.selectAll();
        List<Post> postList = postService.selectAll();
        List<Post> weekHotPostList = postService.selectHotPostByWeek();
        List<WeekUser> weekUserList = userService.selectMostUserByWeek();

        session.setAttribute("moduleList", moduleList);
        session.setAttribute("categoryList", categoryList);
        mv.addObject("postList", postList);
        mv.addObject("weekHotPostList",weekHotPostList);
        mv.addObject("weekUserList",weekUserList);

        mv.setViewName("index");

        return mv;
    }
}
