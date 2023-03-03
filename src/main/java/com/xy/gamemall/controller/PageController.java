package com.xy.gamemall.controller;

import com.xy.gamemall.entity.GameCarousel;
import com.xy.gamemall.entity.GameInfoUpdate;
import com.xy.gamemall.entity.GameType;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import com.xy.gamemall.service.GameCarouselService;
import com.xy.gamemall.service.GameInfoService;
import com.xy.gamemall.service.GameTypeService;
import com.xy.gamemall.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private GameInfoService gameInfoService;

    @Autowired
    private GameCarouselService gameCarouselService;

    @Autowired
    private GameTypeService gameTypeService;



    /**
     * 主页面，初始化数据。
     * @param model
     * @return
     */
    @GetMapping({"/index", "/", "/index.html"})
    public String index(Model model){

        //查询出所有轮播图
        List<GameCarousel> gameCarouselList = gameCarouselService.getAllGameCarousel();

        //查询出前6名的热销商品
        List<GameInfoDTO> gameInfoDTOList = gameInfoService.getHotSall();


        model.addAttribute("gameCarouselList",gameCarouselList);
        model.addAttribute("gameInfoDTOList",gameInfoDTOList);

        return "index";
    }

    //跳转到用户登录界面
    @GetMapping("/login")
    public String loginPage(){
        return "login/login";
    }



    //跳转到用户注册界面
    @GetMapping("/register")
    public String registerPage(){

        return "/login/register";
    }

    //跳转到管理员登录界面
    @GetMapping("/admin/login")
    public String toAdminLoginPage(){
        return "login/adminLogin";
    }


    //跳转到管理员功能界面
    @GetMapping("/admin/index")
    public String adminIndexPage(){
        return "admin/index";
    }


    /**
     * 跳转到游戏添加页面
     * @param model
     * @return
     */
    @GetMapping("/admin/gameInfoAdd")
    public String gameInfoAdd(Model model){
        List<GameType> allGameType = gameTypeService.getAllGameType();
        model.addAttribute("allGameType",allGameType);
        return "admin/gameInfoAdd";
    }

    /**
     * 跳转到游戏修改页面
     * @param gameId
     * @param p
     * @param model
     * @return
     */
    @GetMapping("/admin/gameInfoUpdate/{gameId}/{p}")
    public String gameInfoUpdate(@PathVariable("gameId") Long gameId,@PathVariable("p")Integer p, Model model){
        //游戏类型列表
        List<GameType> allGameType = gameTypeService.getAllGameType();
        model.addAttribute("allGameType",allGameType);
        //游戏信息
        GameInfoDTO gameInfoDTO = gameInfoService.getGameInfoDTOByGameId(gameId,null);
        System.out.println(gameInfoDTO);
        GameInfoUpdate gameInfoUpdate = new GameInfoUpdate(gameInfoDTO);
        System.out.println(gameInfoUpdate);
        model.addAttribute("gameInfoUpdate",gameInfoUpdate);
        model.addAttribute("p",p);
        return "admin/gameInfoUpdate";
    }



    @GetMapping("/test")
    public String test(){
        return "admin/index";
    }



}
