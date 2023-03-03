package com.xy.gamemall.service.impl;

import com.xy.gamemall.dao.AdminUserMapper;
import com.xy.gamemall.entity.AdminUser;
import com.xy.gamemall.service.AdminUserService;
import com.xy.gamemall.utils.MD5Util;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public Result checkAdminUser(AdminUser adminUser, HttpSession session) {

        AdminUser loginAdminUser = adminUserMapper.selectAdminUser(adminUser.getAdminName(), MD5Util.MD5Encode(adminUser.getAdminPassword(),"UTF-8"));

        //有该管理员账号
        if(loginAdminUser != null){
            if (loginAdminUser.getLocked() == 0){       //管理员账号状态正常
                //将管理员账号存储到session中
                session.setAttribute("loginAdminUser",loginAdminUser);
                //删除session中的verifyCode（验证码）
                session.removeAttribute("VerifyCode");
                return Result.success();
            }else {
                return Result.fail("管理员账号被禁用！");
            }
        }else {
            return Result.fail("账号或密码错误！");
        }

    }

    @Override
    public int checkAdminName(String adminName) {
        int n = adminUserMapper.selectAdminName(adminName);
        return n;
    }

    @Override
    public int updateAdminNameById(Long id, String adminName) {
        int n = adminUserMapper.updateAdminNameById(id,adminName);
        return n;
    }

    @Override
    public String selectAdminPasswordById(Long id) {
        return adminUserMapper.selectAdminPasswordById(id);
    }

    @Override
    public int updateAdminPasswordById(Long id,String newPwd) {
        return adminUserMapper.updateAdminPasswordById(id,newPwd);
    }
}
