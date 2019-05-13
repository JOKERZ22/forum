package xin.shaonianyou.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.shaonianyou.forum.entity.Post;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.PostUser;
import xin.shaonianyou.forum.mapper.PostMapper;
import xin.shaonianyou.forum.service.PostService;
import xin.shaonianyou.forum.util.DateUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    //发表帖子
    public Map<String, String> insert(Post post, User user) {

        Map<String, String> resultMap = new HashMap<String, String>();

        if (user == null) {
            resultMap.put("message", "未登录，请先登录");
        } else {
            post.setUser_id(user.getId());
            post.setDate_created(new Date());
            long i = postMapper.insert(post);

            if (i < 1) {
                resultMap.put("message", "插入出错");
            } else {
                resultMap.put("message", "1");
            }
        }
        return resultMap;
    }

    //查询所有帖子
    public List<Post> selectAll() {
        return postMapper.selectAll();
    }

    //查询当前模块下的帖子
    public List<Post> selectByModule(long moduleid) {
        return postMapper.selectByModule(moduleid);
    }

    @Override
    public List<Post> selectByCategory(long categoryid) {
        return postMapper.selectByCategory(categoryid);
    }

    //本周热议
    @Override
    public List<Post> selectHotPostByWeek() {

        DateUtil dateUtil = new DateUtil();
        String weekstart = dateUtil.getWeekStart();
        String weekend = dateUtil.getWeekEnd();

        return postMapper.selectHotPostByWeek(weekstart, weekend);
    }

    @Override
    public Post selectById(long postid) {
        return postMapper.selectById(postid);
    }

    @Override
    public List<Post> selectByPost(Post post) {

        return postMapper.selectByPost(post);
    }

    @Override
    public long selsectCountByUserId(long userid) {
        return postMapper.selsectCountByUserId(userid);
    }

    @Override
    public List<PostUser> selectIndex() {
        return postMapper.selectIndex();
    }

    @Override
    public long postCount() {
        return postMapper.postCount();
    }
}
