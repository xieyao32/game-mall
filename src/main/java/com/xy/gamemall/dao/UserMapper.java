package com.xy.gamemall.dao;


import com.xy.gamemall.entity.Recommend;
import com.xy.gamemall.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int insert(User user);

    int insertSelective(User user);

    User selectByUserName(String userName);

    User isDeleted(String userName);

    User selectByUserNameAndPassword(@Param("userName") String userName,@Param("password") String password);

    List<User> selectAllUser();

    int lockUserIsDeletedByUserId(Long userId);

    int unlockUserIsDeletedByUserId(Long userId);

    List<User> selectUsersByUserName(String userName);

    int updateUserName(@Param("userId") Integer userId, @Param("userName") String userName);

    String getPassWordByUserId(Integer userId);

    int updatePassWord(@Param("userId") Integer userId,@Param("newPwd") String newPwd);

    List<Long> selectUserId();

    List<Recommend> selectUserIdAndGameId(List<Long> userIds);
}
