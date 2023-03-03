package com.xy.gamemall.service;

import com.xy.gamemall.entity.GameType;
import com.xy.gamemall.utils.Result;

import java.util.List;

public interface GameTypeService {
    List<GameType> getAllGameType();

    String getGameTypeByTypeId(Long typeId);

    Result addGameType(GameType gameType);

    Result selectGameTypeById(Long typeId);

    Result updateGameType(GameType gameType);

    Result deleteGameTypeByTypeId(Long typeId);
}
