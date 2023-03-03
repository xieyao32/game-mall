package com.xy.gamemall.service;

import com.xy.gamemall.entity.dto.WishListDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface WishListService {
    List<Long> getWishGameIdsByUserId(Long userId);

    List<WishListDTO> getWishList(HttpSession session);

    int insertToWishList(HttpSession session, Long gameId);

    int removeWishList(HttpSession session, Long gameId);
}
