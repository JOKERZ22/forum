package xin.shaonianyou.forum.mapper;

import xin.shaonianyou.forum.entity.Comment;
import xin.shaonianyou.forum.entity.vo.CommentUser;
import xin.shaonianyou.forum.entity.vo.PostComment;

import java.util.List;

public interface CommentMapper {

    public List<CommentUser> selectCommentListByPostId(long postid);

    public long insert(Comment comment);

    public List<PostComment> selectByUserId(long userid);

    public long commentCount();

    public long commentIncrease(long commentid);

    public Comment selectCommentById(long commentid);
}
