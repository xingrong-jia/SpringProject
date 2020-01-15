package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 21:17
 */
@Data
public class HallInfo implements Serializable {

    private String hallFieldId;

    private String hallName;

    private Integer price;

    private String seatFile;

    private String soldSeats;
}
