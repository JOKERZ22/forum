package xin.shaonianyou.forum.service;

import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.WeekUser;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {

    public Map<String, String> insertUser(User user);

    public Map<String, String> selectUserByEmail(User pageuser, HttpSession session);

    public List<WeekUser> selectMostUserByWeek();

    public User selectById(long id);

    public Map<String, String> repass(String currentpass,String newpass,User usersession);

    public Map<String, String> updateSection(User user);
}
