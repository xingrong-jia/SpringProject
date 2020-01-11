package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 21:13
 */
@Data
public class FieldInfo implements Serializable {

    private FilmListVo filmInfo;

    private CinemaVo cinemaInfo;

    private HallInfo hallInfo;

}
