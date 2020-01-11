package com.stylefeng.guns.pay.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 22:07
 */
@Data
public class OrderPayCinemaVo implements Serializable {

    private Integer cinemaId;

    private String cinemaName;

    private String price;

}
