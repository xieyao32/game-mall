package com.xy.gamemall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.gamemall.dao.GameImgMapper;
import com.xy.gamemall.dao.GameInfoMapper;
import com.xy.gamemall.dao.UserMapper;
import com.xy.gamemall.entity.*;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import com.xy.gamemall.service.GameInfoService;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class GameInfoServiceImpl implements GameInfoService {

    @Autowired
    private GameInfoMapper gameInfoMapper;

    @Autowired
    private GameImgMapper gameImgMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<GameInfoDTO> getAllGameInfoDTO() {
        List<GameInfoDTO> gameInfoDTOS = gameInfoMapper.selectAllGameInfoDTOBySelective(null);
        //给GameInfoDTO的imgs赋值
        for (GameInfoDTO g:gameInfoDTOS){
            Long gameId = g.getGameId();
            List<String> imgs = gameImgMapper.selectAllImgById(gameId);
            g.setImgs(imgs);
        }
        return gameInfoDTOS;
    }

    @Override
    public List<GameInfoDTO> getAllGameInfoDTOBySelective(String gameName,Long typeId) {
        GameInfoDTO gameInfoDTO=new GameInfoDTO();
        gameInfoDTO.setGameName(gameName);
        gameInfoDTO.setGameStatus((byte) 1);    //查询上架的游戏
        gameInfoDTO.setTypeId(typeId);
        List<GameInfoDTO> gameInfoDTOS = gameInfoMapper.selectAllGameInfoDTOBySelective(gameInfoDTO);

        //给GameInfoDTO的imgs赋值
        for (GameInfoDTO g:gameInfoDTOS){
            Long gameId = g.getGameId();
            List<String> imgs = gameImgMapper.selectAllImgById(gameId);
            g.setImgs(imgs);
        }

        return gameInfoDTOS;
    }

    //根据游戏id获取游戏详细信息
    @Override
    public GameInfoDTO getGameInfoDTOByGameId(Long gameId, Byte gameStatus) {
        //创建一个GameInfoDTO用于传输数据
        GameInfoDTO gameInfoDTO=new GameInfoDTO();
        gameInfoDTO.setGameId(gameId);
        gameInfoDTO.setGameStatus(gameStatus);
        //根据条件在数据库中查询数据
        List<GameInfoDTO> gameInfoDTOS = gameInfoMapper.selectAllGameInfoDTOBySelective(gameInfoDTO);
        if (gameInfoDTOS == null){//没有查到
            return null;
        }else { //查找成功
            GameInfoDTO game = gameInfoDTOS.get(0); //取出所需数据
            //由于游戏图片单独一张表，需要单独进行游戏图片的查找
            List<String> imgs = gameImgMapper.selectAllImgById(gameId);
            //将游戏图片数据添加到GameInfoDTO中
            game.setImgs(imgs);
            return game;
        }
    }

    @Override
    public List<GameInfoDTO> getHotSall() {
        List<GameInfoDTO> gameInfoDTOList = gameInfoMapper.selectHotSell();
        //给GameInfoDTO的imgs赋值
        for (GameInfoDTO g:gameInfoDTOList){
            Long gameId = g.getGameId();
            List<String> imgs = gameImgMapper.selectAllImgById(gameId);
            g.setImgs(imgs);
        }
        return gameInfoDTOList;
    }

    @Override
    public List<Long> getOrderGameIdsByUserId(Long userId) {
        List<Long> orderGameIds = gameInfoMapper.selectOrderGameIdsByUserId(userId);
        return orderGameIds;
    }

    @Override
    public Result lockGame(Long gameId) {
        int n = gameInfoMapper.updateGameStatusTo_0(gameId);
        if (n>0){
            return Result.success("成功下架！");
        }else {
            return Result.fail("下架失败！");
        }
    }

    @Override
    public Result unLockGame(Long gameId) {
        int n = gameInfoMapper.updateGameStatusTo_1(gameId);
        if (n>0){
            int i = gameInfoMapper.updateUTime(gameId,new Date());
            return Result.success("成功上架！");
        }else {
            return Result.fail("上架失败！");
        }
    }

    @Override
    public List<GameInfoDTO> getAllGameInfoDTOBySelective_admin(String gameName, Long typeId) {
        GameInfoDTO gameInfoDTO=new GameInfoDTO();
        gameInfoDTO.setGameName(gameName);
        gameInfoDTO.setGameStatus(null);    //查询所有游戏
        gameInfoDTO.setTypeId(typeId);
        List<GameInfoDTO> gameInfoDTOS = gameInfoMapper.selectAllGameInfoDTOBySelective(gameInfoDTO);

        //给GameInfoDTO的imgs赋值
        for (GameInfoDTO g:gameInfoDTOS){
            Long gameId = g.getGameId();
            List<String> imgs = gameImgMapper.selectAllImgById(gameId);
            g.setImgs(imgs);
        }

        return gameInfoDTOS;
    }

    @Override
    public Result deleteGame(Integer gameId) {
        int n = gameInfoMapper.deleteGameInfoByGameId(gameId);
        if (n>0){
            return Result.success("删除成功！");
        }else {
            return Result.fail("删除失败！");
        }
    }

    @Override
    public int addGameInfo(GameInfo gameInfo) {
        return gameInfoMapper.insertGameInfo(gameInfo);
    }

    @Override
    @Transactional      //开启事务
    public int updateGameInfo(GameInfoUpdate gameInfo) {
        //1.更新游戏信息
        int n = gameInfoMapper.updateGameInfo(gameInfo);
        //2.删除游戏图片
        if (n>0){
            int i = gameImgMapper.deleteGameImgByGameId(gameInfo.getGameId());
            //3.更新游戏图片
            GameImg gameImg = new GameImg();
            gameImg.setGameId(gameInfo.getGameId());
            List<String> imgs = new ArrayList<>();
            imgs.add(gameInfo.getMainImg());
            for(String img : gameInfo.getOtherImgs()){
                imgs.add(img);
            }
            gameImg.setGameImgs(imgs);
            gameImgMapper.insertGameImgs(gameImg);
            return 1;
        }
        return 0;
    }

    //游戏推荐
    @Override
    public List<GameInfoDTO> getRecommendGameInfo(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Long userId = loginUser.getUserId();    //获取登录用户的id
        //获取所有用户id
        List<Long> longs = userMapper.selectUserId();

        //定义附近用户id的list
        List<Long> userIds = new ArrayList<>();
        //获取推荐用户所在list中的位置
        int index = longs.indexOf(userId);
        userIds.add(userId); //将推荐用户id存入
        //存入附近的4个用户
        for (int i = 1;userIds.size()<5;i++){
            if (index+i<longs.size() && userIds.size()<5){
                userIds.add(longs.get(index+i));
            }

            if (index-i>=0 && userIds.size()<5){
                userIds.add(longs.get(index-i));
            }
        }
        Collections.sort(userIds);  //自然排序

        List<Recommend> recommends = userMapper.selectUserIdAndGameId(userIds);


        int[][] sparseMatrix = new int[userIds.size()][userIds.size()];//建立用户稀疏矩阵，用于用户相似度计算【相似度矩阵】
        Map<Long, Long> userItemLength = new HashMap<>();//存储每一个用户对应的不同物品总数  eg: A 3
        Map<Long, Set<Long>> itemUserCollection = new HashMap<>();//建立物品到用户的倒排表 eg: a A B
        Set<Long> items = new HashSet<>();//辅助存储物品集合
        Map<Long, Long> userID = new HashMap<>();//辅助存储每一个用户的用户ID映射     用户id  所在下标
        Map<Long, Long> idUser = new HashMap<>();//辅助存储每一个ID对应的用户映射    所在下标  用户id

        for (int i = 0;i< userIds.size();i++){
            Recommend recommend = recommends.get(i);
            userItemLength.put(recommend.getUserId(), (long) (recommend.getGameId() != null ? recommend.getGameId().size() : 0));
            userID.put(recommend.getUserId(), (long) i);
            idUser.put((long) i,recommend.getUserId());
            //建立物品--用户倒排表
            for (int j = 0; j<recommend.getGameId().size(); j++){
                if (items.contains(recommend.getGameId().get(j))){  //如果包含该物品id则直接添加
                    itemUserCollection.get(recommend.getGameId().get(j)).add(recommend.getUserId());
                }else{
                    items.add(recommend.getGameId().get(j));
                    itemUserCollection.put(recommend.getGameId().get(j),new HashSet<Long>());
                    itemUserCollection.get(recommend.getGameId().get(j)).add(recommend.getUserId());
                }
            }

        }

        //计算相似度矩阵【稀疏】
        Set<Map.Entry<Long, Set<Long>>> entrySet = itemUserCollection.entrySet();
        Iterator<Map.Entry<Long, Set<Long>>> iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Set<Long> commonUsers = iterator.next().getValue();
            for (Long user_u : commonUsers){
                for (Long user_v : commonUsers){
                    if (user_u.equals(user_v)){
                        continue;
                    }
                    sparseMatrix[Math.toIntExact(userID.get(user_u))][Math.toIntExact(userID.get(user_v))] += 1;
                }
            }
        }

        Map<Long,Double> itemAndRecommendDegree = new HashMap<>();
        //计算指定用户recommendUser的物品推荐度
        for (Long gameId : items){
            Set<Long> users = itemUserCollection.get(gameId);//得到购买当前物品的所有用户集合
            if (! users.contains(userId)){  //去除被推荐的用户
                Double itemRecommendDegree = 0.0;
                for (Long user : users){
                    itemRecommendDegree += sparseMatrix[Math.toIntExact(userID.get(userId))][Math.toIntExact(userID.get(user))]/Math.sqrt(userItemLength.get(userId)*userItemLength.get(user));//推荐度计算
                }
//                System.out.println("The item "+gameId+" for "+userId +"'s recommended degree:"+itemRecommendDegree);
                itemAndRecommendDegree.put(gameId,itemRecommendDegree);
            }
        }

        List<Long> gameIds = sortMap(itemAndRecommendDegree);
        if (gameIds.size() != 0){
            List<GameInfoDTO> gameInfoDTOList = gameInfoMapper.selectGameInfoDTOByGameIds(gameIds);
            //给GameInfoDTO的imgs赋值
            for (GameInfoDTO g:gameInfoDTOList){
                Long gameId = g.getGameId();
                List<String> imgs = gameImgMapper.selectAllImgById(gameId);
                g.setImgs(imgs);
            }
            return gameInfoDTOList;
        }

        return null;
    }

    //对map进行排序，按double降序排列
    public List<Long> sortMap(Map<Long,Double> newMap){
        List<Map.Entry<Long, Double>> lists = new ArrayList<Map.Entry<Long, Double>>(newMap.entrySet());
        List<Long> recommendGameIds = new ArrayList<>();
        Collections.sort(lists, new Comparator<Map.Entry<Long, Double>>() {
            @Override
            public int compare(Map.Entry<Long, Double> o1, Map.Entry<Long, Double> o2) {
                Double d1 = o1.getValue();
                Double d2 = o2.getValue();
                if (d1<d2){
                    return 1;
                }else if (d1 > d2){
                    return -1;
                }else {
                    return 0;
                }
            }
        });

        for (Map.Entry<Long, Double> set : lists){
            if (recommendGameIds.size()<6){
                recommendGameIds.add(set.getKey());
            }else {
                break;
            }
//            System.out.println(set.getKey()+"  "+set.getValue());
        }
//        System.out.println(recommendGameIds);
        return recommendGameIds;
    }
}
