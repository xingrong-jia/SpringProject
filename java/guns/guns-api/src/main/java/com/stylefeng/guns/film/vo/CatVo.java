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
public class CatVo implements Serializable {

    private String catId;

    private String catName;

    private Boolean isActive;
}
