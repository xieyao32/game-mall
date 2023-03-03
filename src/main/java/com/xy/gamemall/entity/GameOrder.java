package com.xy.gamemall.entity;

import lombok.Data;

import java.util.Date;

@Data
public class GameOrder {

    private Long orderId;

    private Long userId;

    private String userName;

    private Long gameId;

    private String gameName;

    private String gameImg;

    private String typeName;

    private Double totalPrice;

    private Double gameDiscount;

    private String payType;

    private Date payTime;

}
