package com.xy.gamemall.config;

import com.xy.gamemall.interceptor.AdminLoginInterceptor;
import com.xy.gamemall.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将拦截器注册到容器中
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")     //所有请求都被拦截（包括静态资源）
                .excludePathPatterns("/admin/gameInfoUpdate/**","/admin/gameInfoUpdate","/admin/gameInfoAdd","/gameImgManage/images","/gameImgManage/mainImg","/deleteGame/**","/unLockGame/**","/lockGame/**","/deleteGameType/**","/updateGameType","/selectGameTypeById/**","/addGameType","/carouselManage/remove/**","/carouselManage/add/**","/userManage/unlock/**","/userManage/lock/**","/userManage","/gameOrderManage","/admin/gameInfoAdd","/gameInfoManage","/gameTypeManage","/carouselManage","/admin/index","/adminLogin","/adminLogout","/updateAdminName/**","/updateAdminPassword/**","/admin/login","/","/index","/shop","/gameType","/searchByGameType","/gameDetail","/login","/register","/common/kaptcha","/assets/**","/css/**","/file/**","/fonts/**","/gameImg/**","/icon/**","/img/**","/js/**","/upload/**","/plugins/**","/webjars/**");

        registry.addInterceptor(new AdminLoginInterceptor())
                .addPathPatterns("/admin/gameInfoUpdate/**","/admin/gameInfoUpdate","/admin/gameInfoAdd","/gameImgManage/images","/gameImgManage/mainImg","/deleteGame/**","/unLockGame/**","/lockGame/**","/deleteGameType/**","/updateGameType","/selectGameTypeById/**","/addGameType","/carouselManage/remove/**","/carouselManage/add/**","/userManage/unlock/**","/userManage/lock/**","/admin/index","/carouselManage","/gameTypeManage","/gameInfoManage","/admin/gameInfoAdd","/gameOrderManage","/userManage");
    }

}
