package xin.shaonianyou.forum.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.shaonianyou.forum.entity.User;
import xin.shaonianyou.forum.entity.vo.WeekUser;
import xin.shaonianyou.forum.mapper.UserMapper;
import xin.shaonianyou.forum.service.UserService;
import xin.shaonianyou.forum.util.DateUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //注册
    public Map<String, String> insertUser(User pageuser) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        //判断用户名或邮箱是否已被注册
        if (!(userMapper.selectUserByEmail(pageuser.getEmail()) == null || userMapper.selectUserByEmail(pageuser.getEmail()).equals(""))) {
            resultMap.put("message", "邮箱已被注册，请更换");
        } else if (!(userMapper.selectUserByUserName(pageuser.getName()) == null || userMapper.selectUserByUserName(pageuser.getName()).equals(""))) {
            resultMap.put("message", "用户名已存在，请更换");
        } else {
            //密码进行MD5加密
            pageuser.setPassword(DigestUtils.md5Hex(pageuser.getPassword()));

            //插入用户
            long i = userMapper.insertUser(pageuser);
            if (i < 1) {
                resultMap.put("message", "注册失败");
            } else {
                resultMap.put("message", "1");
            }
        }
        return resultMap;
    }

    //通过邮箱登录
    public Map<String, String> selectUserByEmail(User pageuser, HttpSession session) {

        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        //判断登录邮箱是否存在
        if (userMapper.selectUserByEmail(pageuser.getEmail()) == null || userMapper.selectUserByEmail(pageuser.getEmail()).equals("")) {
            resultMap.put("message", "邮箱未注册，请检查");
        } else {
            //根据邮箱查询用户
            User user = userMapper.selectUserByEmail(pageuser.getEmail());

            //判断密码是否正确
            if (!user.getPassword().equals(DigestUtils.md5Hex(pageuser.getPassword()))) {
                resultMap.put("message", "密码错误，请检查");
            } else {
                session.setAttribute("usersession", user);
                resultMap.put("message", "1");
            }
        }
        return resultMap;
    }

    @Override
    public List<WeekUser> selectMostUserByWeek() {

        DateUtil dateUtil = new DateUtil();
        String weekstart = dateUtil.getWeekStart();
        String weekend = dateUtil.getWeekEnd();
        return userMapper.selectMostUserByWeek(weekstart, weekend);
    }

    @Override
    public User selectById(long id) {
        return userMapper.selectById(id);
    }

    //修改密码
    @Override
    public Map<String, String> repass(String currentpass, String newpass, User usersession) {

        Map<String, String> resultMap = new HashMap<String, String>();
        //查询当前用户
        User currentUser = userMapper.selectById(usersession.getId());

        if (currentpass == null || currentpass.equals("")) {
            resultMap.put("message", "输入的当前密码为空！");
        } else if (!(DigestUtils.md5Hex(currentpass).equals(currentUser.getPassword()))) {
            resultMap.put("message", "输入的当前密码错误，请确认密码！");
        } else if (currentUser.getPassword().equals(DigestUtils.md5Hex(newpass))) {
            resultMap.put("message", "未作任何修改");
        } else {
            long i = userMapper.updatePass(DigestUtils.md5Hex(newpass), usersession.getId());
            if (i < 1) {
                resultMap.put("message", "修改失败");
            } else {
                resultMap.put("message", "1");
            }
        }
        return resultMap;
    }

    //修改用户资料
    @Override
    public Map<String, String> updateSection(User user) {

        Map<String, String> resultMap = new HashMap<String, String>();

        if (!(userMapper.selectUserByEmail(user.getEmail()) == null || userMapper.selectUserByEmail(user.getEmail()).equals(""))) {
            resultMap.put("message", "邮箱已被注册，请更换");
        } else if (!(userMapper.selectUserByUserName(user.getName()) == null || userMapper.selectUserByUserName(user.getName()).equals(""))) {
            resultMap.put("message", "用户名已存在，请更换");
        } else {
            long i = userMapper.updateSection(user);
            if (i < 1) {
                resultMap.put("message", "修改失败");
            } else {
                resultMap.put("message", "1");
            }
        }

        return resultMap;
    }

    //通过用户名登录--后台
    @Override
    public Map<String, String> selectUserByUserName(User pageuser, HttpSession session) {
        //用于存放信息
        Map<String, String> resultMap = new HashMap<String, String>();

        //判断用户名是否存在
        if (userMapper.selectUserByUserName(pageuser.getName()) == null || userMapper.selectUserByUserName(pageuser.getName()).equals("")) {
            resultMap.put("message", "用户名不存在，请检查");
        } else {
            //根据用户名查询用户
            User user = userMapper.selectUserByUserName(pageuser.getName());

            //判断密码是否正确
            if (!user.getPassword().equals(DigestUtils.md5Hex(pageuser.getPassword()))) {
                resultMap.put("message", "密码错误，请检查");
            } else if (user.getIs_administrator() < 1) {
                resultMap.put("message", "该用户不是管理员，请检查");
            } else {
                session.setAttribute("adminusersession", user);
                resultMap.put("message", "1");
            }
        }
        return resultMap;
    }

    @Override
    public long userCount() {
        return userMapper.userCount();
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectUserBySection(String start, String end, String username) {
        return userMapper.selectUserBySection(start, end, username);
    }

}
