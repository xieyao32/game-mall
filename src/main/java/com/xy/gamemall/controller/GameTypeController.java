package com.xy.gamemall.controller;

import com.xy.gamemall.entity.GameType;
import com.xy.gamemall.service.GameTypeService;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GameTypeController {

    @Autowired
    private GameTypeService gameTypeService;
    private Long typeId;

    /**
     * 查询所有游戏类型信息
     */
    @GetMapping("/gameType")
    @ResponseBody
    public Result String(){
        List<GameType> gameTypeList = gameTypeService.getAllGameType();
        return Result.success().add("gameTypeList",gameTypeList);
    }

    /**
     * 游戏类型显示
     * @param model
     * @return
     */
    @GetMapping("/gameTypeManage")
    public String gameTypeManage(Model model){
        List<GameType> gameTypeList = gameTypeService.getAllGameType();
        model.addAttribute("gameTypeList",gameTypeList);
        return "admin/gameTypeManage";
    }

    /**
     * 管理员添加游戏类型
     * @param gameType
     * @return
     */
    @PostMapping("/addGameType")
    @ResponseBody
    public Result addGameType(GameType gameType){
        return gameTypeService.addGameType(gameType);
    }

    /**
     * 根据typeId查询游戏类型信息
     * @param typeId
     * @return
     */
    @GetMapping("/selectGameTypeById/{typeId}")
    @ResponseBody
    public Result selectGameTypeById(@PathVariable("typeId")Long typeId){
        return gameTypeService.selectGameTypeById(typeId);
    }

    /**
     * 管理员修改游戏类型信息
     * @param gameType
     * @return
     */
    @PostMapping("/updateGameType")
    @ResponseBody
    public Result updateGameType(GameType gameType){
        return gameTypeService.updateGameType(gameType);
    }

    /**
     * 管理员删除游戏类型
     * @param typeId
     * @return
     */
    @GetMapping("/deleteGameType/{typeId}")
    @ResponseBody
    public Result deleteGameType(@PathVariable("typeId") Long typeId){
        this.typeId = typeId;
        return gameTypeService.deleteGameTypeByTypeId(typeId);
    }

}
