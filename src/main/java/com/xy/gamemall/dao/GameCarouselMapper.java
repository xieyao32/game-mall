package com.xy.gamemall.dao;

import com.xy.gamemall.entity.GameCarousel;
import com.xy.gamemall.entity.dto.CarouselDTO;

import java.util.List;

public interface GameCarouselMapper {

    public List<GameCarousel> selectAll();

    List<CarouselDTO> selectPartGameInfo();

    List<Long> selectCarouselIds();

    int insertToCarousel(Long gameId);

    int deleteFromCarouselByGameId(Long gameId);

    List<CarouselDTO> selectPartGameInfoByGameName(String gameName);
}
