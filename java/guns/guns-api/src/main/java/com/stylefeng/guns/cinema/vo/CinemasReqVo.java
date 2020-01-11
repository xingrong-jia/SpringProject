package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 15:27
 */
@Data
public class CinemasReqVo implements Serializable {

    private Integer brandId = 99;

    private Integer hallType = 99;

    private Integer districtId = 99;

    private Integer pageSize = 12;

    private Integer nowPage = 1;

    private Integer areaId = 99;

}
