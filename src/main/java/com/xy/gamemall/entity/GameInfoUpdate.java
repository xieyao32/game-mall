package com.xy.gamemall.entity;

import com.xy.gamemall.entity.dto.GameInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameInfoUpdate {
    private Long gameId;

    private Long typeId;

    private String gameName;

    private String mainImg;

    private List<String> otherImgs = new ArrayList<>();

    private String gameDesc;

    private String gameReq;

    private Double gamePrice;

    private Double gameDiscount;

    private Integer quantitySale;

    public GameInfoUpdate(GameInfoDTO gameInfoDTO) {
        this.gameId = gameInfoDTO.getGameId();
        this.typeId = gameInfoDTO.getTypeId();
        this.gameName = gameInfoDTO.getGameName();
        this.gameDesc = gameInfoDTO.getGameDesc();
        this.gameReq = gameInfoDTO.getGameReq();
        this.gamePrice = gameInfoDTO.getGamePrice();
        this.gameDiscount = gameInfoDTO.getGameDiscount();
        this.quantitySale = gameInfoDTO.getQuantitySale();
        if (gameInfoDTO.getImgs() != null){
            for (String img : gameInfoDTO.getImgs()){
                if (img.contains("p1.jpg")){
                    this.mainImg = img;
                }else {
                    this.otherImgs.add(img);
                }
            }
        }
    }
}
