package xin.shaonianyou.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.shaonianyou.forum.entity.Comment;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.CommentUser;
import xin.shaonianyou.forum.entity.vo.PostComment;
import xin.shaonianyou.forum.mapper.CommentMapper;
import xin.shaonianyou.forum.mapper.PostMapper;
import xin.shaonianyou.forum.service.CommentService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private PostMapper postMapper;

    @Override
    public List<CommentUser> selectCommentListByPostId(long postid) {
        return commentMapper.selectCommentListByPostId(postid);
    }

    //插入评论
    @Override
    public Map<String, String> insert(Comment comment, HttpSession session) {

        Map<String, String> resultMap = new HashMap<String, String>();

        User user = (User) session.getAttribute("usersession");

        if (user == null) {
            resultMap.put("message", "未登录，请先登录");
        } else {
            comment.setUser_id(user.getId());
            long i = commentMapper.insert(comment);
            if (i < 1) {
                resultMap.put("message", "插入出错");
            } else {
                long k = postMapper.updateCommentCount(comment.getPost_id());
                if (k < 1) {
                    resultMap.put("message", "插入出错");
                }
                resultMap.put("message", "1");
            }
        }

        return resultMap;
    }

    @Override
    public List<PostComment> selectByUserId(long userid) {
        return commentMapper.selectByUserId(userid);
    }

    @Override
    public long commentCount() {
        return commentMapper.commentCount();
    }

    @Override
    public Map<String, String> commentIncrease(long commentid) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        long l = commentMapper.commentIncrease(commentid);
        if (l < 1) {
            resultMap.put("message", "点赞出错");
        } else {
            long k = postMapper.updateHitCount(commentid);
            if (k < 1) {
                resultMap.put("message", "点赞出错");
            } else {
                resultMap.put("message", "1");
            }
        }

        return resultMap;
    }

    @Override
    public Comment selectCommentById(long commentid) {
        return commentMapper.selectCommentById(commentid);
    }
}
