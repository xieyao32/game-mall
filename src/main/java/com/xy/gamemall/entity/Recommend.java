package com.xy.gamemall.entity;

import lombok.Data;

import java.util.List;

@Data
public class Recommend {

    private Long userId;

    private List<Long> gameId;
}
