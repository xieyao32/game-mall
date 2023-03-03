package com.xy.gamemall.service.impl;

import com.xy.gamemall.dao.GameCarouselMapper;
import com.xy.gamemall.entity.GameCarousel;
import com.xy.gamemall.entity.dto.CarouselDTO;
import com.xy.gamemall.service.GameCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class GameCarouselServiceImpl implements GameCarouselService {

    @Autowired
    private GameCarouselMapper gameCarouselMapper;


    @Override
    public List<GameCarousel> getAllGameCarousel() {
        List<GameCarousel> gameCarouselList = gameCarouselMapper.selectAll();
        return gameCarouselList;
    }


    //根据游戏id添加到轮播图
    @Override
    public int addToCarousel(Long gameId, HttpSession session) {
        int n = gameCarouselMapper.insertToCarousel(gameId);
        if (n>0){   //添加成功,将session中的数据更新，用于前端的轮播图展示
            List<Long> carouselIds = (List<Long>) session.getAttribute("carouselIds");
            carouselIds.add(gameId);
            session.setAttribute("carouselIds",carouselIds);
        }
        return n;
    }

    //根据游戏id从轮播图中删除
    @Override
    public int removeFromCarousel(Long gameId, HttpSession session) {
        int n = gameCarouselMapper.deleteFromCarouselByGameId(gameId);
        if(n>0){    //移除成功,将session中的数据更新
            List<Long> carouselIds = (List<Long>) session.getAttribute("carouselIds");
            carouselIds.remove(gameId);
            session.setAttribute("carouselIds",carouselIds);
        }
        return n;
    }

    @Override
    public List<CarouselDTO> getPartGameInfoByGameName(String gameName,HttpSession session) {

        List<CarouselDTO> carouselDTOList = gameCarouselMapper.selectPartGameInfoByGameName(gameName);
        List<Long> carouselIds = gameCarouselMapper.selectCarouselIds();
        session.setAttribute("carouselIds",carouselIds);

        return carouselDTOList;
    }
}
