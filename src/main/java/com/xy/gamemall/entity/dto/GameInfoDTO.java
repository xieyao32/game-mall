package com.xy.gamemall.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xy.gamemall.entity.GameImg;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GameInfoDTO {

    private Long gameId;

    private Long typeId;

    private String typeName;

    private String gameName;

    private List<String> imgs;

    private String gameDesc;

    private String gameReq;

    private String[] reqs;  //将系统要求字符串进行split(";"),分段显示

    private Double gamePrice;

    private Double gameDiscount;

    private Integer quantitySale;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gameCtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gameUtime;

    private Byte gameStatus;

    public void setGameReq(String gameReq) {
        this.gameReq = gameReq;
        String[] split = gameReq.split(";");
        this.reqs=split;
    }
}
