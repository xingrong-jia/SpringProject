package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Region;
import com.jiaxingrong.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @RequestMapping("list")
    public BaseReqVo<List> region(){
        List<Region> regions = regionService.list();
        return new BaseReqVo<>(regions,"成功",0);
    }
}
