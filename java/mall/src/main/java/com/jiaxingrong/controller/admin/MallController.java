package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Region;
import com.jiaxingrong.service.BrandService;
import com.jiaxingrong.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class MallController {

    @Autowired
    RegionService regionService;

    @Autowired
    BrandService brandService;

    @RequestMapping("region/list")
    public BaseReqVo<List> region(){
        List<Region> regions = regionService.list();
        return new BaseReqVo<>(regions,"成功",0);
    }

    @RequestMapping("brand/list")
    public BaseReqVo<Map> brandList(Laypage laypage){
        Map<String,Object> map = brandService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }
}
