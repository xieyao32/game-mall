package com.xy.gamemall.controller;

import com.xy.gamemall.entity.dto.WishListDTO;
import com.xy.gamemall.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WishListController {

    @Autowired
    private WishListService wishListService;

    /**
     * 心愿单数据加载
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/wishList")
    public String toWishList(HttpSession session, Model model){
        List<WishListDTO> wishList = wishListService.getWishList(session);
        model.addAttribute("wishList",wishList);
        return "wishList";
    }


    /**
     * 根据游戏id将游戏添加到心愿单
     * @param gameId
     * @param session
     * @return
     */
    @GetMapping("/addToWishList/{gameId}")
    public String addToWishList(@PathVariable("gameId") Long gameId,HttpSession session){
        int n = wishListService.insertToWishList(session,gameId);
        return "redirect:/gameDetail?gameId="+gameId;
    }


    @GetMapping("/removeWishList/{gameId}")
    public String removeWishList(@PathVariable("gameId") Long gameId,HttpSession session){
        int n = wishListService.removeWishList(session,gameId);
        return "redirect:/gameDetail?gameId="+gameId;
    }

}
