package com.xy.gamemall;

import com.xy.gamemall.dao.GameImgMapper;
import com.xy.gamemall.dao.GameInfoMapper;
import com.xy.gamemall.dao.GameOrderMapper;
import com.xy.gamemall.dao.GameTypeMapper;
import com.xy.gamemall.entity.GameInfo;
import com.xy.gamemall.entity.GameOrder;
import com.xy.gamemall.entity.GameType;
import com.xy.gamemall.entity.User;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import com.xy.gamemall.utils.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class GameMallApplicationTests {

    @Autowired
    private GameTypeMapper gameTypeMapper;
    @Autowired
    private GameImgMapper gameImgMapper;
    @Autowired
    private GameInfoMapper gameInfoMapper;
    @Autowired
    private GameOrderMapper gameOrderMapper;


    @Test
    void contextLoads() {
//        List<GameType> gameTypes = gameTypeMapper.selectAllType();
//        gameTypes.forEach(gameType -> System.out.println(gameType));

//        String s = gameTypeMapper.selectTypeById(2);
//        System.out.println(s);

//        List<String> strings = gameImgMapper.selectAllImgById(1);
//        strings.forEach(s -> System.out.println(s));

//        List<GameInfo> gameInfos = gameInfoMapper.selectAllGames();
////        gameInfos.forEach(gameInfo -> System.out.println(gameInfo));
//        GameInfo g=gameInfos.get(0);
//        System.out.println(g.getGameName());
//        String[] split = g.getGameReq().split(";");
//        for (String s:split){
//            System.out.println(s);
//        }

//        GameInfo g=new GameInfo();
//        g.setGameId(1L);
//        g.setGameName("S");
//        g.setTypeId(2L);
//        g.setGameStatus((byte) 1);
//        List<GameInfo> gameInfos = gameInfoMapper.selectBySelective(g);
//        gameInfos.forEach(gameInfo -> System.out.println(gameInfo));

//        List<GameInfoDTO> gameInfoDTOS = gameInfoMapper.selectAllGameInfoDTO();
//        gameInfoDTOS.forEach(gameInfoDTO -> System.out.println(gameInfoDTO));

//        GameOrder gameOrder = gameOrderMapper.selectGameOrderPartByGameId((long) 1);
//        System.out.println(gameOrder);

//        List<Long> longs = gameOrderMapper.selectPossessGameIdByUserIdAndGameId((long) 1, null);
//        System.out.println(longs.size());

//        List<String> strings = gameImgMapper.selectAllImgById((long) 1);
//        System.out.println(strings.get(0));

        List<GameInfoDTO> gameInfoDTOS = gameInfoMapper.selectHotSell();
        gameInfoDTOS.forEach(g -> System.out.println(g));

    }

    @Test
    public void test2(){
        String admin = MD5Util.MD5Encode("123456", "UTF-8");
        System.out.println(admin);
    }


    @Test
    public void test3(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String buyDate=dateFormat.format(new Date());
        System.out.println(buyDate);
    }

    @Test
    public void test4(){
        int n = gameInfoMapper.updateUTime((long) 29,new Date());
        System.out.println(n);
    }



}
