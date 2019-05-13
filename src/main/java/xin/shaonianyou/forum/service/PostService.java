package xin.shaonianyou.forum.service;

import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.PostUser;

import java.util.List;
import java.util.Map;

public interface PostService {
    public Map<String, String> insert(Post post, User user);

    public List<Post> selectAll();

    public List<Post> selectByModule(long moduleid);

    public List<Post> selectByCategory(long categoryid);

    public List<Post> selectHotPostByWeek();

    public Post selectById(long postid);

    public List<Post> selectByPost(Post post);

    public long selsectCountByUserId(long userid);

    public List<PostUser> selectIndex();

    public long postCount();

}
