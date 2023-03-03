package com.xy.gamemall.entity;

import lombok.Data;

import java.util.List;

@Data
public class GameImg {

    private Long gameId;

    private List<String> gameImgs;
}
