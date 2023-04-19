package com.xy.gamemall.controller.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Controller
public class CommonController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 验证码功能
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @GetMapping("/common/kaptcha")
    public void mallKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/png");

        ShearCaptcha shearCaptcha= CaptchaUtil.createShearCaptcha(110, 40, 4, 2);

        // 验证码存入session
        //httpServletRequest.getSession().setAttribute("VerifyCode1", shearCaptcha);

        //验证码缓存在Redis中，有效期5分钟
        redisTemplate.opsForValue().set("VerifyCode",shearCaptcha.getCode(),5, TimeUnit.MINUTES);

        // 输出图片流
        shearCaptcha.write(httpServletResponse.getOutputStream());
    }




}
