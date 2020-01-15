package com.stylefeng.guns.promo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-13 13:00
 */
@Data
public class PromoRespVo implements Serializable {

    private String cinemaAddress;

    private Integer cinemaId;

    private String cinemaName;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    private String imgAddress;

    private Integer price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    private Integer status;

    private Integer stock;

    private Integer uuid;
}
