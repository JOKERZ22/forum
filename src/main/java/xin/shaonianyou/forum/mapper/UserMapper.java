package xin.shaonianyou.forum.mapper;

import org.apache.ibatis.annotations.Param;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.WeekUser;

import java.util.List;

public interface UserMapper {

    public long insertUser(User user);

    public User selectUserByEmail(String email);

    public User selectUserByUserName(String username);

    public List<WeekUser> selectMostUserByWeek(@Param("weekstart") String weekstart, @Param("weekend") String weekend);

    public User selectById(long id);

    public  long updatePass(@Param("newpass")String newpass,@Param("userid")long userid);

    public long updateSection(User user);

    public long userCount();

    public List<User> selectAll();

    public List<User> selectUserBySection(@Param("start")String start,@Param("end")String end,@Param("username")String username);

}
