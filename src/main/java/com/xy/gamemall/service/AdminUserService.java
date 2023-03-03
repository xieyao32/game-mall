package com.xy.gamemall.service;

import com.xy.gamemall.entity.AdminUser;
import com.xy.gamemall.utils.Result;

import javax.servlet.http.HttpSession;

public interface AdminUserService {
    Result checkAdminUser(AdminUser adminUser, HttpSession session);

    int checkAdminName(String adminName);

    int updateAdminNameById(Long id, String adminName);

    String selectAdminPasswordById(Long id);

    int updateAdminPasswordById(Long id,String newPwd);
}
