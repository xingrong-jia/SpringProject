package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 21:43
 */
@Data
public class FilmInfo implements Serializable {

    private String filmId;

    private Integer filmType;

    private String imgAddress;

    private String filmName;

    private String filmScore;

    private Integer expectNum;

    private String showTime;

    private Integer boxNum;

    private String score;

}
