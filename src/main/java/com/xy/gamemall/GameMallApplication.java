package com.xy.gamemall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.xy.gamemall.dao")      //开启mapper扫描
@EnableTransactionManagement  //开启事务支持
public class GameMallApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(GameMallApplication.class, args);

    }
}
