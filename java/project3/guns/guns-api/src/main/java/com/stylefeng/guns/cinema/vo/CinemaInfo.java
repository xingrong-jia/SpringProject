package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 16:01
 */
@Data
public class CinemaInfo implements Serializable {

    private Integer uuid;

    private String cinemaName;

    private String address;

    private Double minimumPrice;
}
