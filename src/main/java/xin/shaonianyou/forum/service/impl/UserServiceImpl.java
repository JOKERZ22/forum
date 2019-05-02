package xin.shaonianyou.forum.service.impl;

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

        //用于存放查询信息
        Map<String, String> resultMap = new HashMap<String, String>();

        //判断用户名或邮箱是否已被注册
        if (!(userMapper.selectUserByEmail(pageuser.getEmail()) == null || userMapper.selectUserByEmail(pageuser.getEmail()).equals(""))) {
            resultMap.put("message", "邮箱已被注册，请更换");
        } else if (!(userMapper.selectUserByUserName(pageuser.getName()) == null || userMapper.selectUserByUserName(pageuser.getName()).equals(""))) {
            System.out.println(userMapper.selectUserByUserName(pageuser.getName()));
            resultMap.put("message", "用户名已存在，请更换");
        } else {
            //密码进行MD5加密
            pageuser.setPassword(DigestUtils.md5Hex(pageuser.getPassword()));

            //插入用户
            int i = userMapper.insertUser(pageuser);
            if (i < 1) {
                resultMap.put("message", "注册失败");
            } else {
                resultMap.put("message", "1");
            }
        }
        return resultMap;
    }

    //通过邮箱登陆
    public Map<String, String> selectUserByEmail(User pageuser, HttpSession session) {

        //用于存放查询信息
        Map<String, String> resultMap = new HashMap<String, String>();

        //判断登陆邮箱是否存在
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
        return userMapper.selectMostUserByWeek(weekstart,weekend);
    }

}
