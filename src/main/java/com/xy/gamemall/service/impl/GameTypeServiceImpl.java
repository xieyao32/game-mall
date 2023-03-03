package com.xy.gamemall.service.impl;

import com.xy.gamemall.dao.GameTypeMapper;
import com.xy.gamemall.entity.GameType;
import com.xy.gamemall.service.GameTypeService;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameTypeServiceImpl implements GameTypeService {

    @Autowired
    private GameTypeMapper gameTypeMapper;

    @Override
    public List<GameType> getAllGameType() {
        List<GameType> gameTypes = gameTypeMapper.selectAllType();
        return gameTypes;
    }

    @Override
    public String getGameTypeByTypeId(Long typeId) {
        return gameTypeMapper.selectTypeById(typeId);
    }

    //游戏类型添加
    @Override
    public Result addGameType(GameType gameType) {
        //1.检查是否重名
        int n = gameTypeMapper.checkTypeName(gameType.getTypeName());
        if (n == 0){  //2.没重名则插入
            int i = gameTypeMapper.insertGameType(gameType);
            if (i>0){   //3.插入成功
                return Result.success("添加成功！");
            }else {
                return Result.fail("添加失败！");
            }
        }else {
            return Result.fail("游戏类型名重名！");
        }
    }

    @Override
    public Result selectGameTypeById(Long typeId) {
        //1.根据typeId查询游戏类型信息
        String typeName = gameTypeMapper.selectTypeById(typeId);
        return Result.success(typeName);
    }

    //游戏类型修改
    @Override
    public Result updateGameType(GameType gameType) {
        //1.判断是否重名
        int n = gameTypeMapper.checkTypeName(gameType.getTypeName());
        //2.没有重名则修改
        if (n == 0){
            int i = gameTypeMapper.updateGameType(gameType);
            if (i>0){       //修改成功
                return Result.success("修改成功！");
            }else {
                return Result.fail("修改失败！");
            }
        }
        return Result.fail("你未做修改！");
    }

    //游戏类型删除
    @Override
    @Transactional  //开启事务，防止游戏类型删除成功，而相关游戏没有删除
    public Result deleteGameTypeByTypeId(Long typeId) {
        //1.删除游戏类型
        int n = gameTypeMapper.deleteGameTypeByTypeId(typeId);
        if (n>0){
            //2.删除该游戏类型的游戏
            int i = gameTypeMapper.deleteGameInfoByTypeId(typeId);
            return Result.success("删除成功！");
        }else{
            return Result.fail("删除失败！");
        }
    }
}
