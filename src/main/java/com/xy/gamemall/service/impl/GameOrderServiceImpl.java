package com.xy.gamemall.service.impl;

import com.xy.gamemall.dao.GameImgMapper;
import com.xy.gamemall.dao.GameOrderMapper;
import com.xy.gamemall.entity.GameImg;
import com.xy.gamemall.entity.GameOrder;
import com.xy.gamemall.entity.User;
import com.xy.gamemall.service.GameOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameOrderServiceImpl implements GameOrderService {

    @Autowired
    private GameOrderMapper gameOrderMapper;

    @Autowired
    private GameImgMapper gameImgMapper;

    @Override
    public Boolean GameIdIsExistByUserIdAndGameId(Long userId, Long gameId) {
        List<Long> longs = gameOrderMapper.selectPossessGameIdByUserIdAndGameId(userId, gameId);
        if (longs.size() == 0){
            return false;
        }else {
            return true;
        }
    }


    /**
     * 提交订单
     * @param user
     * @param gameId
     * @param payType
     * @return
     */
    @Override
    @Transactional  //开启事务，防止付钱后出现异常，导致订单未加入和游戏销售数量没有增加。
    public int submitOrder(User user, Long gameId, String payType) {
        //根据游戏id将部分游戏信息存入到GameOrder中
        GameOrder gameOrder = gameOrderMapper.selectGameOrderPartByGameId(gameId);
        gameOrder.setUserId(user.getUserId());
        gameOrder.setUserName(user.getUserName());
        gameOrder.setPayType(payType);
        //给图片赋值
        List<String> imgs = gameImgMapper.selectAllImgById(gameId);
        if (imgs.size() != 0){
            gameOrder.setGameImg(imgs.get(0));
        }else {
            gameOrder.setGameImg("/gameImg/default.jpg"); //如果该游戏没有上传图片，则赋值为默认图片
        }
        //添加订单数据到数据库
        int i = gameOrderMapper.insertSelective(gameOrder);
        //增加购买游戏的销售量
        gameOrderMapper.updateQuantitySale(gameId);
        return i;
    }

    @Override
    public List<GameOrder> getAllGameOrderByUserId(Long userId) {
        List<GameOrder> orders = gameOrderMapper.selectAllGameOrderByUserId(userId);
        if (orders.size() == 0){
            return null;
        }
        return orders;
    }

    @Override
    public List<GameOrder> getAllGameOrder() {
        List<GameOrder> gameOrders = gameOrderMapper.selectAllGameOrder();
        return gameOrders;
    }

    @Override
    public List<GameOrder> getAllGameOrderByUserName(String userName) {

        List<GameOrder> gameOrders = gameOrderMapper.selectAllGameOrderByUserName(userName);

        return gameOrders;
    }
}
