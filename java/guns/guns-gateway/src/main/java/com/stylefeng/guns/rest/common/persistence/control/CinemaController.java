package com.stylefeng.guns.rest.common.persistence.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiaxingrong.utils.CollectionUtils;
import com.stylefeng.guns.cinema.CinemaService;
import com.stylefeng.guns.cinema.vo.CinemaConditionRespVo;
import com.stylefeng.guns.cinema.vo.CinemasReqVo;
import com.stylefeng.guns.cinema.vo.FieldInfo;
import com.stylefeng.guns.cinema.vo.HallInfo;
import com.stylefeng.guns.rest.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-10 15:21
 */
@RestController
@RequestMapping("cinema")
public class CinemaController {

    @Reference(interfaceClass = CinemaService.class)
    private CinemaService cinemaService;

    @RequestMapping("getCinemas")
    public Result getCinemas(CinemasReqVo cinemasReqVo){
        Map<String,Object> map = cinemaService.getCinemas(cinemasReqVo);
        if (map==null||map.size()==0) return new Result(999,"系统出现异常，请联系管理员!");
        List data = (List) map.get("data");
        if (CollectionUtils.isEmpty(data)) return new Result(1,"影院信息查询失败!");
        Integer nowPage = (Integer) map.get("nowPage");
        Integer totalPage = (Integer) map.get("totalPage");
        return Result.ok(data, nowPage, totalPage);
    }

    @RequestMapping("getCondition")
    public Result getCondition(CinemasReqVo cinemasReqVo){
        CinemaConditionRespVo condition = cinemaService.getCondition(cinemasReqVo);
        if (condition==null) return new Result(999,"系统出现异常，请联系管理员!");
        if (CollectionUtils.isEmpty(condition.getAreaList())||CollectionUtils.isEmpty(condition.getBrandList())||CollectionUtils.isEmpty(condition.getHalltypeList())) return new Result(1,"影院信息查询失败!");

        return Result.ok(condition);
    }

    @RequestMapping("getFields")
    public Result getFields(Integer cinemaId){
        Map<String,Object> map = cinemaService.getFields(cinemaId);
        if (map==null||map.size()==0) return new Result(999,"系统出现异常，请联系管理员!");

        if (CollectionUtils.isEmpty((List)map.get("filmList"))||map.get("cinemaInfo")==null) return new Result(1,"影院信息查询失败!");

        return Result.ok(map);
    }

    @RequestMapping("getFieldInfo")
    public Result getFieldInfo(Integer cinemaId,Integer fieldId){
        FieldInfo fieldInfo = cinemaService.getFieldInfo(cinemaId,fieldId);
        if (fieldInfo==null) return new Result(999,"系统出现异常，请联系管理员!");

        if (fieldInfo.getCinemaInfo()==null||fieldInfo.getFilmInfo()==null||fieldInfo.getHallInfo()==null) return new Result(1,"影院信息查询失败!");

        return Result.ok(fieldInfo);
    }

}
