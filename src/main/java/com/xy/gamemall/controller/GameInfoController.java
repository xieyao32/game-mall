package com.xy.gamemall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.gamemall.entity.DataJson;
import com.xy.gamemall.entity.GameImg;
import com.xy.gamemall.entity.GameInfo;
import com.xy.gamemall.entity.GameInfoUpdate;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import com.xy.gamemall.service.GameImgService;
import com.xy.gamemall.service.GameInfoService;
import com.xy.gamemall.service.GameTypeService;
import com.xy.gamemall.utils.GameImgUploadUtil;
import com.xy.gamemall.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class GameInfoController {

    @Autowired
    private GameInfoService gameInfoService;

    @Autowired
    private GameTypeService gameTypeService;

    @Autowired
    private GameImgService gameImgService;


    /**
     * 商店页面数据展示
     * @param gameName
     * @param p
     * @param model
     * @return
     */
    @GetMapping("/shop")
    public String shopPage(@RequestParam(value = "gameName",defaultValue = "")String gameName,
                           @RequestParam(value = "p",defaultValue = "1") Integer p, Model model){
        //进行分页
        PageHelper.startPage(p,6);  //每页6个数据
        List<GameInfoDTO> allGameInfoDTO = gameInfoService.getAllGameInfoDTOBySelective(gameName,null);
        PageInfo<GameInfoDTO> pageInfo = new PageInfo<>(allGameInfoDTO,3);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("gameName",gameName);
        return "shop";
    }

    /**
     * 通过游戏类型进行分类
     * @param typeId
     * @param gameName
     * @param p
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/searchByGameType")
    public String searchByGameType(@RequestParam(value = "typeId",required = false) Long typeId,
                                   @RequestParam(value = "gameName",defaultValue = "") String gameName,
                                   @RequestParam(value = "p",defaultValue = "1") Integer p,
                                   Model model,
                                   HttpSession session){
        if (typeId == null){
            typeId= (Long) session.getAttribute("typeId");
        }
        //进行分页
        PageHelper.startPage(p,6);
        //查询对应类别的所有在售游戏
        List<GameInfoDTO> allGameInfoDTO = gameInfoService.getAllGameInfoDTOBySelective(gameName,typeId);
        PageInfo<GameInfoDTO> pageInfo = new PageInfo<>(allGameInfoDTO,3);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("gameName",gameName);
        session.setAttribute("typeId",typeId);
        if (allGameInfoDTO != null){
            model.addAttribute("gameType",allGameInfoDTO.get(0).getTypeName());
        }else{
            model.addAttribute("gameType",gameTypeService.getGameTypeByTypeId(typeId));
        }
        return "gameType";
    }


    /**
     * 用户查看游戏详细信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/gameDetail")
    public String gameDetail(@RequestParam("gameId") Long id,Model model){

        //根据id查询上架的游戏
        GameInfoDTO gameInfoDTO = gameInfoService.getGameInfoDTOByGameId(id, (byte) 1);
        model.addAttribute("game",gameInfoDTO);
        return "gameDetail";
    }


    /**
     * 管理员进行游戏商品管理
     * @param model
     * @return
     */
    @GetMapping("/gameInfoManage")
    public String gameInfoManage(@RequestParam(name = "gameName",defaultValue = "") String gameName,@RequestParam(value = "p",defaultValue = "1") Integer p,Model model,HttpSession session){

        PageHelper.startPage(p,6);  //进行分页
        List<GameInfoDTO> allGameInfoDTO = gameInfoService.getAllGameInfoDTOBySelective_admin(gameName,null);
        PageInfo<GameInfoDTO> pageInfo = new PageInfo<>(allGameInfoDTO,5);
        model.addAttribute("allGameInfoDTO",pageInfo);
        model.addAttribute("gameName",gameName);


        return "admin/gameInfoManage";
    }

    /**
     * 管理员商品下架
     * @param gameId
     * @return
     */
    @GetMapping("/lockGame/{gameId}")
    @ResponseBody
    public Result lockGame(@PathVariable("gameId") Long gameId){

        return gameInfoService.lockGame(gameId);

    }

    /**
     * 管理员将商品上架
     * @param gameId
     * @return
     */
    @GetMapping("/unLockGame/{gameId}")
    @ResponseBody
    public Result unLockGame(@PathVariable("gameId") Long gameId){

        return gameInfoService.unLockGame(gameId);

    }

    /**
     * 游戏删除
     * @param gameId
     * @return
     */
    @GetMapping("/deleteGame/{gameId}")
    @ResponseBody
    public Result deleteGame(@PathVariable("gameId") Integer gameId){
        return gameInfoService.deleteGame(gameId);
    }


    /**
     * 上传游戏商品主图
     * @param file
     * @return
     */
    @PostMapping("/gameImgManage/mainImg")
    @ResponseBody
    public DataJson gameImgManageImage(@RequestParam(value = "file",required = false)MultipartFile file){
        DataJson dataJson = new DataJson();

        String imagePath = GameImgUploadUtil.mainImgUpload(file);
        System.out.println(imagePath);

        if (imagePath != null){
            HashMap hashMap = new HashMap();
            hashMap.put("src",imagePath);
            dataJson.setCode(0);
            dataJson.setMsg("上传成功");
            dataJson.setData(hashMap);
        }else {
            dataJson.setCode(-1);
            dataJson.setMsg("上传失败");
        }

        return dataJson;
    }


    /**
     * 上传多张图片
     * @param files
     * @return
     */
    @PostMapping("/gameImgManage/images")
    @ResponseBody
    public DataJson gameImgManageImages(@RequestParam(value = "file",required = false) MultipartFile[] files){
        DataJson dataJson = new DataJson();

        List<String> imageUrls = new ArrayList<>();
        HashMap hashMap = new HashMap<>();

        for (MultipartFile file:files){
            String imagePath = GameImgUploadUtil.upload(file);
            imageUrls.add(imagePath);
        }
        hashMap.put("src",imageUrls);
        dataJson.setCode(0);
        dataJson.setData(hashMap);
        return dataJson;
    }

    /**
     * 游戏添加
     * @param gameInfo
     * @param mainImgPath
     * @param otherImgs
     * @return
     */
    @PostMapping("/admin/gameInfoAdd")
    @ResponseBody
    public String gameInfoAdd(GameInfo gameInfo,String mainImgPath,String[] otherImgs){
        //1.判断游戏信息是否为空
        if (gameInfo != null){
            gameInfo.setGameStatus((byte) 0);//未上架状态
            //2.不为空则添加到数据库
            int n = gameInfoService.addGameInfo(gameInfo);
            if (n>0){       //3.游戏添加成功则继续添加游戏图片
                //4.游戏图片不为空则添加
                if ( (mainImgPath != null && mainImgPath != "") || otherImgs != null){
                    //为游戏图片赋值
                    GameImg gameImg = new GameImg();
                    gameImg.setGameId(gameInfo.getGameId());
                    List<String> imgs = new ArrayList<>();
                    if (mainImgPath != null){
                        imgs.add(mainImgPath);
                    }
                    if (otherImgs != null){
                        for (String img : otherImgs){
                            imgs.add(img);
                        }
                    }
                    gameImg.setGameImgs(imgs);
                    int i = gameImgService.addGameImgs(gameImg);    //插入图片
                }
                return "1";
            }else {
                return "0";
            }
        }else {
            return "0";
        }
    }


    /**
     * 游戏信息修改
     * @param gameInfo
     * @return
     */
    @PostMapping("/admin/gameInfoUpdate")
    @ResponseBody
    public String gameInfoUpdate(GameInfoUpdate gameInfo){
        System.out.println(gameInfo);
        //更新游戏信息
        int n = gameInfoService.updateGameInfo(gameInfo);
        if (n>0){
            return "1";
        }
        return "0";
    }



}
