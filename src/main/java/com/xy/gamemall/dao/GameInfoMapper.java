package com.xy.gamemall.dao;

import com.xy.gamemall.entity.GameInfo;
import com.xy.gamemall.entity.GameInfoUpdate;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GameInfoMapper {
    public List<GameInfo> selectAllGames();

    public List<GameInfo> selectBySelective(GameInfo gameInfo);

    public List<GameInfoDTO> selectAllGameInfoDTO();

    public List<GameInfoDTO> selectAllGameInfoDTOBySelective(GameInfoDTO gameInfoDTO);

    public List<GameInfoDTO> selectHotSell();

    List<Long> selectOrderGameIdsByUserId(Long userId);

    int updateGameStatusTo_0(Long gameId);

    int updateGameStatusTo_1(Long gameId);

    int deleteGameInfoByGameId(Integer gameId);

    int insertGameInfo(GameInfo gameInfo);

    int updateGameInfo(GameInfoUpdate gameInfo);

    int updateUTime(@Param("gameId") Long gameId, @Param("date") Date date);

    List<GameInfoDTO> selectGameInfoDTOByGameIds(List<Long> gameIds);
}
