package com.stylefeng.guns.pay.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-12 9:45
 */
@Data
public class PayRespVo implements Serializable {

    private String orderId;

    private Integer orderStatus;

    private String orderMsg;

}
