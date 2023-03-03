package com.xy.gamemall.dao;

import com.xy.gamemall.entity.dto.WishListDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WishListMapper {

    public List<Long> selectWishGameIdsByUserId(Long userId);

    List<WishListDTO> selectWishListDTOByGameIds(List<Long> wishGameIds);

    int insertToWishList(@Param("userId") Long userId, @Param("gameId") Long gameId);

    int deleteWishListItemByUserIdAndGameId(@Param("userId") Long userId,@Param("gameId") Long gameId);
}
