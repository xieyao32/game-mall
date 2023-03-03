package com.xy.gamemall.service;


import com.github.pagehelper.PageInfo;
import com.xy.gamemall.entity.GameInfo;
import com.xy.gamemall.entity.GameInfoUpdate;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import com.xy.gamemall.utils.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface GameInfoService {

    public List<GameInfoDTO> getAllGameInfoDTO();

    public List<GameInfoDTO> getAllGameInfoDTOBySelective(String gameName,Long typeId);

    public GameInfoDTO getGameInfoDTOByGameId(Long gameId,Byte gameStatus);


    List<GameInfoDTO> getHotSall();

    List<Long> getOrderGameIdsByUserId(Long userId);

    Result lockGame(Long gameId);

    Result unLockGame(Long gameId);

    List<GameInfoDTO> getAllGameInfoDTOBySelective_admin(String gameName, Long typeId);

    Result deleteGame(Integer gameId);

    int addGameInfo(GameInfo gameInfo);

    int updateGameInfo(GameInfoUpdate gameInfo);

    List<GameInfoDTO> getRecommendGameInfo(HttpSession session);
}

