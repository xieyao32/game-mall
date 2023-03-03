package com.xy.gamemall.dao;

import com.xy.gamemall.entity.GameImg;

import java.util.List;

public interface GameImgMapper {
    public List<String> selectAllImgById(Long id);

    int insertGameImgs(GameImg gameImg);

    int deleteGameImgByGameId(Long gameId);
}
