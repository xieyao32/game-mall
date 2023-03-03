package com.xy.gamemall.service.impl;

import com.xy.gamemall.dao.UserMapper;
import com.xy.gamemall.entity.User;
import com.xy.gamemall.service.UserService;
import com.xy.gamemall.utils.MD5Util;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户注册功能
     * @param userName
     * @param password
     * @return
     */
    @Override
    public String register(String userName, String password) {
        if (userMapper.selectByUserName(userName) != null){
            return "该用户名已被注册";
        }
        User user=new User();
        user.setUserName(userName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        user.setPasswordMd5(passwordMD5);
        if (userMapper.insertSelective(user) > 0){
            return "注册成功";
        }
        return "注册失败";
    }


    /**
     * 用户登录功能
     * @param userName
     * @param password
     * @return
     */
    @Override
    public String login(String userName, String password, HttpSession httpSession) {
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        User user = userMapper.selectByUserNameAndPassword(userName,passwordMD5);
        if (user != null){
            //当用户账号已被删除时，则登录失败
            if (user.getIsDeleted() == 1){
                return "该用户已被注销";
            }else{
                //将用户登录信息存到session中
                httpSession.setAttribute("loginUser",user);
                return "登录成功";
            }
        }
        return "登录失败";
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userMapper.selectAllUser();
        return users;
    }

    @Override
    public int lockUserById(Long userId) {
        int n = userMapper.lockUserIsDeletedByUserId(userId);
        return n;
    }

    @Override
    public int unlockUserById(Long userId) {
        int n =userMapper.unlockUserIsDeletedByUserId(userId);
        return n;
    }

    @Override
    public List<User> getUsersByUserName(String userName) {

        List<User> users = userMapper.selectUsersByUserName(userName);

        return users;
    }

    @Override
    public Result updateUserName(Integer userId, String userName,HttpSession session) {
        User user = userMapper.selectByUserName(userName);
        if (user != null){
            return Result.fail("用户名已存在");
        }else {
            int n = userMapper.updateUserName(userId,userName);
            if (n>0){
                User loginUser = (User) session.getAttribute("loginUser");
                loginUser.setUserName(userName);
                session.setAttribute("loginUser",loginUser);
                return Result.success("用户名修改成功");
            }else {
                return Result.fail("用户名修改失败");
            }
        }

    }

    @Override
    public Result updatePassWord(Integer userId, String oldPwd, String newPwd) {
        System.out.println(oldPwd+""+newPwd);
        //根据用户id查询密码（经MD5加密后的）
        String pwdMD5 = userMapper.getPassWordByUserId(userId);
        System.out.println("pwdMD5-----"+pwdMD5);
        String oldPwd_MD5 = MD5Util.MD5Encode(oldPwd, "UTF-8");
        System.out.println("oldPwd_MD5----"+oldPwd_MD5);
        //密码相同则进行修改
        if (pwdMD5.equals(oldPwd_MD5)){
            String newPwd_MD5 = MD5Util.MD5Encode(newPwd, "UTF-8");
            int n = userMapper.updatePassWord(userId,newPwd_MD5);
            if (n>0){   //修改成功
                return Result.success("密码修改成功!");
            }else {     //修改失败
                return Result.success("密码修改失败!");
            }
        }else {
            return Result.fail("密码错误！");
        }
    }
}
