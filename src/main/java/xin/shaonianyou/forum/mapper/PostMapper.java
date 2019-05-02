package xin.shaonianyou.forum.mapper;

import org.apache.ibatis.annotations.Param;
import xin.shaonianyou.forum.entity.Post;

import java.util.List;

public interface PostMapper {

    public int insert(Post post);

    public List<Post> selectAll();

    public List<Post> selectByModule(int moduleid);

    public List<Post> selectByCategory(int categoryid);

    public List<Post> selectHotPostByWeek(@Param("weekstart") String weekstart, @Param("weekend") String weekend);
}
