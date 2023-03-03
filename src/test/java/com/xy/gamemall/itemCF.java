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
        //获取所有用户id
        List<Long> longs = userMapper.selectUserId();
        System.out.println(longs);
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
        System.out.println(userIds);
        List<Recommend> recommends = userMapper.selectUserIdAndGameId(userIds);
        System.out.println(recommends);

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
