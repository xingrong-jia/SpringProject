package com.stylefeng.guns.order.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 14:16
 */
@Data
public class OrderField implements Serializable {

    private Integer uUID;

    private Integer cinema_id;

    private Integer film_id;

    private String begin_time;

    private String end_time;

    private Integer hall_id;

    private String hall_name;

    private Integer price;
}
