package com.xy.gamemall.service.impl;

import com.xy.gamemall.dao.WishListMapper;
import com.xy.gamemall.entity.User;
import com.xy.gamemall.entity.dto.WishListDTO;
import com.xy.gamemall.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListMapper wishListMapper;

    @Override
    public List<Long> getWishGameIdsByUserId(Long userId) {
        return wishListMapper.selectWishGameIdsByUserId(userId);
    }

    @Override
    public List<WishListDTO> getWishList(HttpSession session) {
        //获取该用户信息
        User user = (User) session.getAttribute("loginUser");
        //获取该用户的心愿单游戏id
        List<Long> wishGameIds = (List<Long>) session.getAttribute("wishGameIds");
        if(wishGameIds.size() != 0){
            //根据心愿单游戏id查询游戏信息
            List<WishListDTO> wishList = wishListMapper.selectWishListDTOByGameIds(wishGameIds);
            return wishList;
        }

        return null;
    }

    @Override
    public int insertToWishList(HttpSession session, Long gameId) {
        //获取该用户信息
        User user = (User) session.getAttribute("loginUser");
        //获取session中的心愿单游戏id数据
        List<Long> wishGameIds = (List<Long>) session.getAttribute("wishGameIds");

        if (wishGameIds.contains(gameId)){
            return 0; // 0表示已经存在
        }else {
            //不存在则插入
            int n = wishListMapper.insertToWishList(user.getUserId(),gameId);

            if (n>0){//插入成功则更新session中的心愿单游戏id数据
                wishGameIds.add(gameId);
                session.setAttribute("wishGameIds",wishGameIds);
                return 1; // 1表示成功添加到心愿单
            }else {
                return -1; // -1表示添加到心愿单失败
            }
        }
    }

    @Override
    public int removeWishList(HttpSession session, Long gameId) {

        //获取该用户信息
        User user = (User) session.getAttribute("loginUser");
        //获取session中的心愿单游戏id数据
        List<Long> wishGameIds = (List<Long>) session.getAttribute("wishGameIds");

        if (wishGameIds.contains(gameId)){
            int n = wishListMapper.deleteWishListItemByUserIdAndGameId(user.getUserId(),gameId);
            if (n > 0){
                wishGameIds.remove(gameId);
                session.setAttribute("wishGameIds",wishGameIds);
                return 1;  //1表示成功
            }else {
                return -1;  //-1表示失败
            }
        }else {
            //不存在
            return 1;   //1表示成功
        }

    }
}
