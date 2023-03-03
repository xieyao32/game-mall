package com.xy.gamemall.dao;

import com.xy.gamemall.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

public interface AdminUserMapper {
    AdminUser selectAdminUser(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword);

    int selectAdminName(String adminName);

    int updateAdminNameById(@Param("id") Long id,@Param("adminName") String adminName);

    String selectAdminPasswordById(Long id);

    int updateAdminPasswordById(@Param("id") Long id,@Param("newPwd") String newPwd);
}
