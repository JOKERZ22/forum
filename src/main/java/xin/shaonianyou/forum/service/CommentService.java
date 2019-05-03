package xin.shaonianyou.forum.service;

import xin.shaonianyou.forum.entity.Comment;
import xin.shaonianyou.forum.entity.vo.CommentUser;
import xin.shaonianyou.forum.entity.vo.PostComment;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface CommentService {

    public List<CommentUser> selectCommentListByPostId(long postid);

    public Map<String,String> insert(Comment comment,HttpSession session);

    public List<PostComment> selectByUserId(long userid);
}
