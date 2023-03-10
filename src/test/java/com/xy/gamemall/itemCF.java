package com.xy.gamemall;

import com.xy.gamemall.dao.GameInfoMapper;
import com.xy.gamemall.dao.UserMapper;
import com.xy.gamemall.entity.Recommend;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class itemCF {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GameInfoMapper gameInfoMapper;

    @Test
    public void test1(){
        Long[] longs = new Long[]{};
        List<Long> longs1 = Arrays.asList(longs);
        List<Long> userId = new ArrayList<>();
        int index = longs1.indexOf(new Long(4));
        if (index+2<= longs1.size() && index-2>=0){
            List<Long> longs2 = longs1.subList(index - 2, index + 2 + 1);
            System.out.println(longs2);
        }
        else{
            int count = 1;
            userId.add((long) 4);
            for (int i = 1;count<5;i++){
                if (index+i<=longs1.size() && count<5){
                    userId.add(longs1.get(index+i));
                }
                if (index-i>=0 && count<5){
                    userId.add(longs1.get(index-i));
                }
            }
        }
        System.out.println(Arrays.toString(longs));
    }

    @Test
    public void test2(){
        Long[] longs = new Long[]{};
        List<Long> longs1 = Arrays.asList(longs);
        List<Long> userId = new ArrayList<>();
        int index = longs1.indexOf(new Long(1));
        int count = 1;
        userId.add((long) 1);
        for (int i = 1;count<5;i++){
            if (index+i<longs1.size() && count<5){
                userId.add(longs1.get(index+i));
                count++;
            }
            if (index-i>=0 && count<5){
                userId.add(longs1.get(index-i));
                count++;
            }
        }
        Collections.sort(userId);
        System.out.println(userId);
    }



    @Test
    public void test3(){
        itemRecommend((long) 1);
    }

    public void itemRecommend(Long userId){
        //??????????????????id
        List<Long> longs = userMapper.selectUserId();
        System.out.println(longs);
        //??????????????????id???list
        List<Long> userIds = new ArrayList<>();
        //????????????????????????list????????????
        int index = longs.indexOf(userId);
        userIds.add(userId); //???????????????id??????
        //???????????????4?????????
        for (int i = 1;userIds.size()<5;i++){
            if (index+i<longs.size() && userIds.size()<5){
                userIds.add(longs.get(index+i));
            }

            if (index-i>=0 && userIds.size()<5){
                userIds.add(longs.get(index-i));
            }
        }
        Collections.sort(userIds);  //????????????
        System.out.println(userIds);
        List<Recommend> recommends = userMapper.selectUserIdAndGameId(userIds);
        System.out.println(recommends);

        int[][] sparseMatrix = new int[userIds.size()][userIds.size()];//???????????????????????????????????????????????????????????????????????????
        Map<Long, Long> userItemLength = new HashMap<>();//????????????????????????????????????????????????  eg: A 3
        Map<Long, Set<Long>> itemUserCollection = new HashMap<>();//????????????????????????????????? eg: a A B
        Set<Long> items = new HashSet<>();//????????????????????????
        Map<Long, Long> userID = new HashMap<>();//????????????????????????????????????ID??????     ??????id  ????????????
        Map<Long, Long> idUser = new HashMap<>();//?????????????????????ID?????????????????????    ????????????  ??????id

        for (int i = 0;i< userIds.size();i++){
            Recommend recommend = recommends.get(i);
            userItemLength.put(recommend.getUserId(), (long) (recommend.getGameId() != null ? recommend.getGameId().size() : 0));
            userID.put(recommend.getUserId(), (long) i);
            idUser.put((long) i,recommend.getUserId());
            //????????????--???????????????
            for (int j = 0; j<recommend.getGameId().size(); j++){
                if (items.contains(recommend.getGameId().get(j))){  //?????????????????????id???????????????
                    itemUserCollection.get(recommend.getGameId().get(j)).add(recommend.getUserId());
                }else{
                    items.add(recommend.getGameId().get(j));
                    itemUserCollection.put(recommend.getGameId().get(j),new HashSet<Long>());
                    itemUserCollection.get(recommend.getGameId().get(j)).add(recommend.getUserId());
                }
            }

        }

        //?????????????????????????????????
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
        //??????????????????recommendUser??????????????????
        for (Long gameId : items){
            Set<Long> users = itemUserCollection.get(gameId);//?????????????????????????????????????????????
            if (! users.contains(userId)){  //????????????????????????
                Double itemRecommendDegree = 0.0;
                for (Long user : users){
                    itemRecommendDegree += sparseMatrix[Math.toIntExact(userID.get(userId))][Math.toIntExact(userID.get(user))]/Math.sqrt(userItemLength.get(userId)*userItemLength.get(user));//???????????????
                }
                System.out.println("The item "+gameId+" for "+userId +"'s recommended degree:"+itemRecommendDegree);
                itemAndRecommendDegree.put(gameId,itemRecommendDegree);
            }
        }


        System.out.println(userItemLength);
        System.out.println(itemUserCollection);

        List<Long> gameIds = sortMap(itemAndRecommendDegree);

        List<GameInfoDTO> gameInfoDTOList = gameInfoMapper.selectGameInfoDTOByGameIds(gameIds);
        System.out.println(gameInfoDTOList);


    }

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
            System.out.println(set.getKey()+"  "+set.getValue());
        }
        System.out.println(recommendGameIds);

        return recommendGameIds;

    }



}
