package xin.shaonianyou.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.shaonianyou.forum.entity.Comment;
import xin.shaonianyou.forum.service.CommentService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addcomment")
    @ResponseBody
    public Map<String, String> insert(Comment comment,HttpSession session) {
        return commentService.insert(comment,session);
    }
}
