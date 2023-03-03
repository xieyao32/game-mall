package com.xy.gamemall.controller;

import cn.hutool.captcha.ShearCaptcha;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.gamemall.entity.User;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import com.xy.gamemall.service.GameInfoService;
import com.xy.gamemall.service.UserService;
import com.xy.gamemall.service.WishListService;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WishListService wishListService;

    @Autowired
    private GameInfoService gameInfoService;



    /**
     * 用户登录
     * @param userName
     * @param verifyCode
     * @param password
     * @param httpSession
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam("userName") String userName,
                        @RequestParam("verifyCode") String verifyCode,
                        @RequestParam("password") String password,
                        HttpSession httpSession){

        //取得session中的验证码
        ShearCaptcha shearCaptcha = (ShearCaptcha) httpSession.getAttribute("VerifyCode");
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            return Result.fail("验证码错误");
        }

        //验证码输入正确则继续执行
        String msg = userService.login(userName, password,httpSession);
        if (msg.equals("该用户已被注销")){
            return Result.fail("该用户已被注销");
        }else if(msg.equals("登录成功")){
            //删除session中的verifyCode（验证码）
            httpSession.removeAttribute("VerifyCode");

            User user = (User) httpSession.getAttribute("loginUser");

            Long userId = user.getUserId();

            //登录成功后查询出心愿单的游戏id
            List<Long> wishGameIds = wishListService.getWishGameIdsByUserId(userId);
            httpSession.setAttribute("wishGameIds",wishGameIds);

            //登录成功后查出已购买的游戏id
            List<Long> orderGameIds = gameInfoService.getOrderGameIdsByUserId(userId);
            httpSession.setAttribute("orderGameIds",orderGameIds);

            //查询出用户可能喜欢的游戏
            List<GameInfoDTO> recommendGameInfo = gameInfoService.getRecommendGameInfo(httpSession);
            httpSession.setAttribute("recommendGameInfo",recommendGameInfo);

            return Result.success("登录成功");
        }


        return Result.fail("用户名或密码错误!");
    }


    /**
     * 用户注册
     * @param userName
     * @param verifyCode
     * @param password
     * @param httpSession
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("userName") String userName,
                           @RequestParam("verifyCode") String verifyCode,
                           @RequestParam("password") String password,
                           HttpSession httpSession){
        //取得session中的验证码
        ShearCaptcha shearCaptcha = (ShearCaptcha) httpSession.getAttribute("VerifyCode");
        if (shearCaptcha == null || !shearCaptcha.verify(verifyCode)) {
            return Result.fail("验证码错误");
        }
        //验证码输入正确则继续执行
        String msg = userService.register(userName, password);

        //判断注册是否成功
        if (msg.equals("注册成功")){
            //删除session中的verifyCode（验证码）
            httpSession.removeAttribute("VerifyCode");
            return Result.success("注册成功");
        }else if(msg.equals("该用户名已被注册")){
            return Result.fail("该用户名已被注册");
        }

        return Result.fail("注册失败");
    }

    /**
     * 用户退出
     * @param session
     */
    @GetMapping("/logout")
    @ResponseBody
    public Result logout(HttpSession session){
        //用户退出后，清理掉session中的信息
        session.removeAttribute("loginUser");
        session.removeAttribute("wishGameIds");
        session.removeAttribute("orderGameIds");
        session.removeAttribute("recommendGameInfo");
        return Result.success("退出成功！");
    }


    /**
     * 修改普通用户的用户名
     * @param userId
     * @param userName
     * @param session
     * @return
     */
    @GetMapping("/updateUserName/{userId}/{userName}")
    @ResponseBody
    public Result updateUserName(@PathVariable("userId")Integer userId,@PathVariable("userName")String userName,HttpSession session){
        return userService.updateUserName(userId,userName,session);
    };


    /**
     * 普通用户修改密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @GetMapping("/updatePassword/{userId}/{oldPwd}/{newPwd}")
    @ResponseBody
    public Result updatePassword(@PathVariable("userId")Integer userId,@PathVariable("oldPwd")String oldPwd,@PathVariable("newPwd")String newPwd){
        if (newPwd != oldPwd){
            return userService.updatePassWord(userId,oldPwd,newPwd);
        }else {
            return Result.fail("两次密码相同！");
        }
    }


    /**
     * 管理员进行用户管理功能
     * @param model
     * @return
     */
    @GetMapping("/userManage")
    public String userManageSearch(@RequestParam(name = "userName",defaultValue = "") String userName,@RequestParam(value = "p",defaultValue = "1") Integer p,Model model){

        //开始分页
        PageHelper.startPage(p,5);
        List<User> users = userService.getUsersByUserName(userName);
        PageInfo<User> userPageInfo = new PageInfo<>(users,5);
        model.addAttribute("users",userPageInfo);
        model.addAttribute("userName",userName);

        return "admin/userManage";
    }


    /**
     * 管理员注销用户功能
     * @param userId
     * @return
     */
    @GetMapping("/userManage/lock/{userId}")
    @ResponseBody
    public Result lockUser(@PathVariable("userId") Long userId){
        int n = userService.lockUserById(userId);

        if (n>0){
            return Result.success("注销成功");
        }else {
            return Result.fail("注销失败");
        }
    }


    /**
     * 管理员恢复用户功能
     * @param userId
     * @return
     */
    @GetMapping("/userManage/unlock/{userId}")
    @ResponseBody
    public Result unlockUser(@PathVariable("userId") Long userId){
        int n = userService.unlockUserById(userId);
        if (n>0){
            return Result.success("恢复成功");
        }else {
            return Result.fail("恢复失败");
        }
    }





}
