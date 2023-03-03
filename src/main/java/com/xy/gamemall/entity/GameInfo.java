package com.xy.gamemall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class GameInfo {

    private Long gameId;

    private Long typeId;

    private String gameName;

    private String gameDesc;

    private String gameReq;

    private Double gamePrice;

    private Double gameDiscount;

    private Integer quantitySale;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gameCtime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gameUtime;

    private Byte gameStatus;
}
