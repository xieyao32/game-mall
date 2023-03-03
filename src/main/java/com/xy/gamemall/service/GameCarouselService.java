package com.xy.gamemall.service;

import com.xy.gamemall.entity.GameCarousel;
import com.xy.gamemall.entity.dto.CarouselDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface GameCarouselService {
    List<GameCarousel> getAllGameCarousel();

    int addToCarousel(Long gameId, HttpSession session);

    int removeFromCarousel(Long gameId, HttpSession session);

    List<CarouselDTO> getPartGameInfoByGameName(String gameName,HttpSession session);
}
