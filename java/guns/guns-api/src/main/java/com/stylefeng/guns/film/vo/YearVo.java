package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-09 16:12
 */
@Data
public class YearVo implements Serializable {

    private String yearId;

    private String yearName;

    private Boolean isActive;
}
