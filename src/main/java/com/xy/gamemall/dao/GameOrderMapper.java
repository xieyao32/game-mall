package com.xy.gamemall.dao;

import com.xy.gamemall.entity.GameOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameOrderMapper {

    public GameOrder selectGameOrderPartByGameId(Long gameId);

    public int insertSelective(GameOrder gameOrder);

    public List<Long> selectPossessGameIdByUserIdAndGameId(@Param("userId") Long userId,@Param("gameId") Long gameId);

    public List<GameOrder> selectAllGameOrder();

    public List<GameOrder> selectAllGameOrderByUserId(Long userId);


    int updateQuantitySale(Long gameId);

    List<GameOrder> selectAllGameOrderByUserName(String userName);
}
