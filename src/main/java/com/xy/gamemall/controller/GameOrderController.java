package com.xy.gamemall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.gamemall.entity.GameOrder;
import com.xy.gamemall.entity.User;
import com.xy.gamemall.entity.dto.GameInfoDTO;
import com.xy.gamemall.service.GameInfoService;
import com.xy.gamemall.service.GameOrderService;
import com.xy.gamemall.utils.Result;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class GameOrderController {

    @Autowired
    private GameOrderService gameOrderService;

    @Autowired
    private GameInfoService gameInfoService;

    /**
     * 用户购买游戏
     * @param gameId
     * @param payType
     * @param session
     * @return
     */
    @PostMapping("/order")
    @ResponseBody
    public Result putOrder(@RequestParam("gameId") Long gameId, @RequestParam("payType") String payType, HttpSession session){

        User user = (User) session.getAttribute("loginUser");
        Boolean isExist = gameOrderService.GameIdIsExistByUserIdAndGameId(user.getUserId(),gameId);
        if (isExist){
            return Result.fail("该游戏已购买");
        }else {
            int n = gameOrderService.submitOrder(user,gameId,payType);

            if (n > 0){
                List<Long> orderGameIds = (List<Long>) session.getAttribute("orderGameIds");
                orderGameIds.add(gameId);
                session.setAttribute("orderGameIds",orderGameIds);


                //更新游戏推荐
                List<GameInfoDTO> recommendGameInfo = gameInfoService.getRecommendGameInfo(session);
                session.setAttribute("recommendGameInfo",recommendGameInfo);

                return Result.success("购买成功");
            }else {
                return Result.fail("购买失败,钱已退回");
            }
        }

    }

    /**
     * 订单展示
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/order")
    public String showOrder(HttpSession session, Model model){
        User user= (User) session.getAttribute("loginUser");
        List<GameOrder> orders = gameOrderService.getAllGameOrderByUserId(user.getUserId());
        model.addAttribute("gameOrders",orders);
        return "gameStore";
    }

    /**
     * 游戏下载
     * @param gameName
     * @param response
     * @throws IOException
     */
    @GetMapping("/download/{gameName}")
    public void downLoad(@PathVariable("gameName") String gameName,
                         HttpServletResponse response) throws IOException {
        String fileName = gameName+".exe";
        //获取下载文件的路径
        String realPaht = ResourceUtils.getURL("classpath:").getPath() + "/static/file/game.exe";
        //获取文件输入流
        FileInputStream is = new FileInputStream(new File(realPaht));

        response.setHeader("content-disposition",
                "attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
    }


    /**
     * 管理员查看订单信息
     * @param userName
     * @param model
     * @return
     */
    @GetMapping("/gameOrderManage")
    public String search(@RequestParam(name = "userName",defaultValue = "") String userName,@RequestParam(value = "p",defaultValue = "1") Integer p,Model model){

        //开始分页
        PageHelper.startPage(p,6);
        List<GameOrder> gameOrders = gameOrderService.getAllGameOrderByUserName(userName);
        PageInfo<GameOrder> gameOrderPageInfo = new PageInfo<>(gameOrders,5);
        model.addAttribute("gameOrders",gameOrderPageInfo);

        model.addAttribute("userName",userName);

        return "admin/gameOrderManage";
    }

}
