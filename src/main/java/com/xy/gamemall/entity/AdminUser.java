package com.xy.gamemall.entity;

import lombok.Data;

@Data
public class AdminUser {

    private Long id;

    private String adminName;

    private String adminPassword;

    private Byte locked;

}
