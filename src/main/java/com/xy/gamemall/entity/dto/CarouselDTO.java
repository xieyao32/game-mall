package com.xy.gamemall.entity.dto;

import lombok.Data;

@Data
public class CarouselDTO {

    private Long gameId;

    private String gameImg;

    private String gameName;

    private String typeName;

    private Integer quantitySale;

    private Byte gameStatus;

}
