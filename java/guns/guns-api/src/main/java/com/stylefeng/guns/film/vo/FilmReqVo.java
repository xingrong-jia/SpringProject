package com.stylefeng.guns.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-09 17:13
 */
@Data
public class FilmReqVo implements Serializable {

    private Integer showType = 1;

    private Integer sortId = 1;

    private Integer sourceId = 99;

    private Integer catId = 99;

    private Integer yearId = 99;

    private Integer nowPage = 1;

    private Integer pageSize = 18;


}
