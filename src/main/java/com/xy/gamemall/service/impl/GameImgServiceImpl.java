package com.xy.gamemall.service.impl;

import com.xy.gamemall.dao.GameImgMapper;
import com.xy.gamemall.entity.GameImg;
import com.xy.gamemall.service.GameImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameImgServiceImpl implements GameImgService {

    @Autowired
    private GameImgMapper gameImgMapper;

    @Override
    public int addGameImgs(GameImg gameImg) {
        return gameImgMapper.insertGameImgs(gameImg);
    }
}
