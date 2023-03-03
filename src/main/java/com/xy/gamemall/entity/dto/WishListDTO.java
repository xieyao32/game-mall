package com.xy.gamemall.entity.dto;

import lombok.Data;

@Data
public class WishListDTO {

    private Long gameId;

    private String gameImg;

    private String typeName;

    private String gameName;

    private String totalPrice;

}
