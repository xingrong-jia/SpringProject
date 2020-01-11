package com.stylefeng.guns.cinema.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 16:48
 */
@Data
public class CinemaConditionRespVo implements Serializable {

    private List<AreaVo> areaList;

    private List<BrandVo> brandList;

    private List<HallTypeVo> halltypeList;
}
