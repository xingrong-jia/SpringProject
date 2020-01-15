package com.stylefeng.guns.mq.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-14 20:52
 */
@Data
public class MQVo implements Serializable {

    private Integer promoId;

    private Integer userId;

    private Integer amount;

    public MQVo(Integer promoId, Integer amount) {
        this.promoId = promoId;
        this.amount = amount;
    }

    public MQVo() {
    }

    public MQVo(Integer promoId, Integer userId, Integer amount) {
        this.promoId = promoId;
        this.userId = userId;
        this.amount = amount;
    }
}
