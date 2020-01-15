package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 16:52
 */
@Data
public class HallTypeVo implements Serializable {

    private Boolean active;

    private Integer halltypeId;

    private String halltypeName;
}
