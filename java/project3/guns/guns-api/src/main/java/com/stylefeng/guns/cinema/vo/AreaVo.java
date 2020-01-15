package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 16:50
 */
@Data
public class AreaVo implements Serializable {

    private Boolean active;

    private Integer areaId;

    private String areaName;
}
