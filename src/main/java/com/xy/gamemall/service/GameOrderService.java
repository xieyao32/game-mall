package com.xy.gamemall.service;

import com.xy.gamemall.entity.GameOrder;
import com.xy.gamemall.entity.User;

import java.util.List;

public interface GameOrderService {
    Boolean GameIdIsExistByUserIdAndGameId(Long userId, Long gameId);

    int submitOrder(User user, Long gameId, String payType);

    List<GameOrder> getAllGameOrderByUserId(Long userId);

    List<GameOrder> getAllGameOrder();

    List<GameOrder> getAllGameOrderByUserName(String userName);
}
