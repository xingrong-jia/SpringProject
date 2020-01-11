package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 17:34
 */
@Data
public class FilmFields implements Serializable {

    private String beginTime;

    private String endTime;

    private Integer fieldId;

    private String hallName;

    private String language;

    private String price;

}
