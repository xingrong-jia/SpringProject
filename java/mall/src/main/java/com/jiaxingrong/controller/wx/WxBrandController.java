package com.jiaxingrong.controller.wx;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/brand")
public class WxBrandController {


    @Autowired
    BrandService brandService;

    @RequestMapping("detail")
    public BaseReqVo detail(Integer id){
        return BaseReqVo.ok(brandService.detail(id));
    }
}
