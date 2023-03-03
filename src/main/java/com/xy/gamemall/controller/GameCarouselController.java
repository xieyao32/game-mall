package com.xy.gamemall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.gamemall.entity.dto.CarouselDTO;
import com.xy.gamemall.service.GameCarouselService;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GameCarouselController {

    @Autowired
    private GameCarouselService gameCarouselService;


    /**
     * 轮播图管理
     * @param model
     * @param session
     * @return
     */
//    @GetMapping("/carouselManage")
//    public String carouselManage(Model model, HttpSession session){
//
//        List<CarouselDTO> carouselDTOList = gameCarouselService.getPartGameInfo(session);
//
//        model.addAttribute("carouselDTOList",carouselDTOList);
//
//        return "admin/carouselManage";
//    }


    @GetMapping("/carouselManage")
    public String searchByGameName(@RequestParam(name = "gameName",defaultValue = "") String gameName,@RequestParam(value = "p",defaultValue = "1") Integer p,Model model,HttpSession session){

        //开始分页
        PageHelper.startPage(p,4);
        List<CarouselDTO> carouselDTOList = gameCarouselService.getPartGameInfoByGameName(gameName,session);
        PageInfo<CarouselDTO> carouselDTOPageInfo = new PageInfo<>(carouselDTOList,5);
        model.addAttribute("carouselDTOList",carouselDTOPageInfo);

        model.addAttribute("gameName",gameName);

        return "admin/carouselManage";
    }


    /**
     * 将游戏添加到轮播图
     * @param gameId
     * @param session
     * @return
     */
    @GetMapping("/carouselManage/add/{gameId}")
    @ResponseBody
    public Result add(@PathVariable("gameId") Long gameId,HttpSession session){

        int n = gameCarouselService.addToCarousel(gameId,session);

        if (n>0){
            return Result.success("添加成功");
        }else {
            return Result.fail("添加失败");
        }

    }


    /**
     * 将游戏从轮播图删除
     * @param gameId
     * @param session
     * @return
     */
    @GetMapping("/carouselManage/remove/{gameId}")
    @ResponseBody
    public Result remove(@PathVariable("gameId") Long gameId,HttpSession session){

        int n = gameCarouselService.removeFromCarousel(gameId,session);

        if (n>0){
            return Result.success("移除成功");
        }else {
            return Result.fail("移除失败");
        }

    }


}
