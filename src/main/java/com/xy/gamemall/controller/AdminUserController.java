package com.xy.gamemall.controller;

import cn.hutool.captcha.ShearCaptcha;
import com.xy.gamemall.entity.AdminUser;
import com.xy.gamemall.service.AdminUserService;
import com.xy.gamemall.utils.MD5Util;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;


    /**
     * 管理员登录
     * @param adminUser
     * @param verifyCode
     * @param session
     * @return
     */
    @PostMapping("/adminLogin")
    @ResponseBody
    public Result adminLogin(AdminUser adminUser, @RequestParam("verifyCode")String verifyCode, HttpSession session){
        //取得session中的验证码
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute("VerifyCode");
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)){
            return Result.fail("验证码错误");
        }

        //效验用户名和密码
        return adminUserService.checkAdminUser(adminUser,session);

    }


    /**
     * 管理员退出登录
     * @param session
     * @return
     */
    @GetMapping("/adminLogout")
    @ResponseBody
    public Result adminLogout(HttpSession session){
        //用户退出后，清理掉session中的信息
        session.removeAttribute("loginAdminUser");

        return Result.success("成功退出!");
    }


    /**
     * 修改管理员用户名
     * @param id
     * @param adminName
     * @param session
     * @return
     */
    @GetMapping("/updateAdminName/{id}/{adminName}")
    @ResponseBody
    public Result updateAdminName(@PathVariable("id")Long id,@PathVariable("adminName")String adminName,HttpSession session){
        //1.判断是否重名
        int n = adminUserService.checkAdminName(adminName);
        //2.没有重名则修改
        if(n == 0){
            int i = adminUserService.updateAdminNameById(id,adminName);
            if (i>0){   //修改成功,更新session
                AdminUser loginAdminUser = (AdminUser) session.getAttribute("loginAdminUser");
                loginAdminUser.setAdminName(adminName);
                session.setAttribute("loginAdminUser",loginAdminUser);
                return Result.success("修改成功！");
            }else {     //修改失败
                return Result.fail("修改失败！");
            }
        }else {
            return Result.fail("用户名已存在！");
        }
    }


    @GetMapping("/updateAdminPassword/{id}/{oldPwd}/{newPwd}")
    @ResponseBody
    public Result updateAdminPassword(@PathVariable("id")Long id,@PathVariable("oldPwd")String oldPwd,@PathVariable("newPwd")String newPwd,HttpSession session){
        //1.查询出原密码
        String pwd = adminUserService.selectAdminPasswordById(id);
        //2.与原密码比较，正确则进行修改
        if (pwd.equals(MD5Util.MD5Encode(oldPwd,"UTF-8"))){
            int n = adminUserService.updateAdminPasswordById(id,MD5Util.MD5Encode(newPwd,"UTF-8"));
            if(n>0){
                session.removeAttribute("loginAdminUser");
                return Result.success("密码修改成功！");
            }else {
                return Result.fail("密码修改失败！");
            }
        }else {     //与原密码不相同
            return Result.fail("原密码错误！");
        }

    }


}
