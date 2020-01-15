package com.stylefeng.guns.promo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-13 17:59
 */
@Data
public class PromoReqVo implements Serializable {

    private Integer promoId;

    private Integer userId;

    private Integer amount;

    private String promoToken;
}
