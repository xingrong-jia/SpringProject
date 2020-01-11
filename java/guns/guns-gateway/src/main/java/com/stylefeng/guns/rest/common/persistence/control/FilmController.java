package com.stylefeng.guns.rest.common.persistence.control;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jiaxingrong.utils.CollectionUtils;
import com.stylefeng.guns.film.FilmService;
import com.stylefeng.guns.film.vo.*;
import com.stylefeng.guns.rest.model.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-08 21:22
 */
@RestController
@RequestMapping("film")
public class FilmController {

    @Reference(interfaceClass = FilmService.class)
    private FilmService filmService;

    @RequestMapping("getIndex")
    public Result getIndex() {
        Map map = filmService.getIndex();
        if (map.isEmpty() || map.size() == 0) {
            return new Result(999, "系统出现异常，请联系管理员!");
        }
        FilmRespInfo data = (FilmRespInfo) map.get("data");
        if (CollectionUtils.isEmpty(data.getBanners())) {
            return new Result(1, "查询失败，无banner可加载!");
        }
        String imgPre = (String) map.get("imgPre");
        return Result.ok(imgPre, data);
    }

    @RequestMapping("getConditionList")
    public Result getConditionList(FilmConditionVo filmConditionVo) {
        FilmConditionRespVo filmConditionRespVo = filmService.getConditionList(filmConditionVo);
        if (filmConditionRespVo == null) return new Result(999, "系统出现异常，请联系管理员！");
        if (filmConditionRespVo.getYearInfo() == null || filmConditionRespVo.getCatInfo() == null || filmConditionRespVo.getSourceInfo() == null)
            return new Result(1, "查询失败，无条件可加载！");
        return Result.ok(filmConditionRespVo);
    }

    @RequestMapping("getFilms")
    public Result getFilms(FilmReqVo reqVo) {
        List<FilmInfo> filmInfos = filmService.getFilms(reqVo);
        if (filmInfos == null) return new Result(999, "系统出现异常，请联系管理员!");
        if (filmInfos.size() == 0) return new Result(1, "查询失败，无banner可加载!");
        return Result.ok(filmInfos);
    }

    @RequestMapping("films/{idOrName}")
    public Result getFilmDetails(@PathVariable String idOrName){

        FilmDetailsVo filmDetails = filmService.getFilmDetails(idOrName);
        if (filmDetails == null) return new Result(999, "系统出现异常，请联系管理员!");
        if (filmDetails.getFilmName() == null) return new Result(1, "查询失败，无影片可加载!");
        return Result.ok(filmDetails);
    }
}
