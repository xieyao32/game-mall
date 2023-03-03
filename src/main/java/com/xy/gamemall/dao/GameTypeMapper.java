package com.xy.gamemall.dao;


import com.xy.gamemall.entity.GameType;

import java.util.List;

public interface GameTypeMapper {

    public List<GameType> selectAllType();

    public String selectTypeById(Long id);

    int checkTypeName(String typeName);

    int insertGameType(GameType gameType);

    int updateGameType(GameType gameType);

    int deleteGameTypeByTypeId(Long typeId);

    int deleteGameInfoByTypeId(Long typeId);


}
