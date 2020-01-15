package com.stylefeng.guns.order.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 11:33
 */
@Data
public class OrderRespVo implements Serializable {

    private String orderId;

    private String filmName;

    private String fieldTime;

    private String cinemaName;

    private String seatsName;

    private String orderPrice;

    private String orderStatus;

    private String orderTimestamp;

}
