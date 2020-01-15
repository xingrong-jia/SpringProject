package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 17:30
 */
@Data
public class CinemaVo implements Serializable {

    private Integer cinemaId;

    private String cinemaName;

    private String cinemaAddress;

    private String cinemaPhone;

    private String imgUrl;
}
