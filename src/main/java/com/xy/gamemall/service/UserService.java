package com.xy.gamemall.service;

import com.xy.gamemall.entity.User;
import com.xy.gamemall.utils.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    String register(String userName, String password);

    String login(String userName, String password, HttpSession httpSession);

    List<User> getAllUsers();

    int lockUserById(Long userId);

    int unlockUserById(Long userId);

    List<User> getUsersByUserName(String userName);

    Result updateUserName(Integer userId, String userName,HttpSession session);

    Result updatePassWord(Integer userId, String oldPwd, String newPwd);
}
