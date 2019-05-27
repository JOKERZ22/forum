package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import xin.shaonianyou.forum.entity.Category;
import xin.shaonianyou.forum.entity.Module;
import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.entity.vo.PostUser;
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

        //查询首页所需的各种信息

        //版块
        List<Module> moduleList = moduleService.selectAll();
        //类别
        List<Category> categoryList = categoryService.selectAll();
        //帖子列表
        List<PostUser> postUserList = postService.selectIndex();
        //本周热议
        List<Post> weekHotPostList = postService.selectHotPostByWeek();
        //回帖周榜
        List<WeekUser> weekUserList = userService.selectMostUserByWeek();

        session.setAttribute("moduleList", moduleList);
        session.setAttribute("categoryList", categoryList);
        mv.addObject("postUserList", postUserList);
        mv.addObject("weekHotPostList",weekHotPostList);
        mv.addObject("weekUserList",weekUserList);

        mv.setViewName("index");

        return mv;
    }
}
