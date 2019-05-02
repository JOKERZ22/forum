package xin.shaonianyou.forum.service;

import xin.shaonianyou.forum.entity.Post;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface PostService {
    public Map<String ,String> insert(Post post, HttpSession session);

    public List<Post> selectAll();

    public List<Post> selectByModule(int moduleid);

    public List<Post> selectByCategory(int categoryid);

    public List<Post> selectHotPostByWeek();
}
