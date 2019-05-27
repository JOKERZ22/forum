package xin.shaonianyou.forum.mapper;

import org.apache.ibatis.annotations.Param;
import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.entity.vo.PostSearch;
import xin.shaonianyou.forum.entity.vo.PostUser;

import java.util.List;

public interface PostMapper {

    public long insert(Post post);

    public List<Post> selectAll();

    public List<PostUser> selectByModule(long moduleid);

    public List<Post> selectByCategory(long categoryid);

    public List<Post> selectHotPostByWeek(@Param("weekstart") String weekstart, @Param("weekend") String weekend);

    public Post selectById(long postid);

    public List<Post> selectByPost(Post post);

    public long selsectCountByUserId(long userid);

    public long updateCommentCount(long postid);

    public List<PostUser> selectIndex();

    public long postCount();

    public long updateHitCount(long commentid);

    public List<PostUser> postSearch(PostSearch postSearch);
}
