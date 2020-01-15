package com.stylefeng.guns.order.vo;

import com.jiaxingrong.utils.StringTool;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-11 11:26
 */
@Data
public class OrderReqVo implements Serializable {

    private Integer fieldId;

    private List<Integer> soldSeats;

    private String seatsName;

    public void setSoldSeats(String soldSeats) {
        this.soldSeats = StringTool.stringToIntList(soldSeats);
    }
}
