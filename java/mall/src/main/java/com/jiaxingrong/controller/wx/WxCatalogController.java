package com.jiaxingrong.controller.wx;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.service.CateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/catalog")
public class WxCatalogController {

    @Autowired
    CateGoryService cateGoryService;

    @RequestMapping("index")
    public BaseReqVo index(){
        return BaseReqVo.ok(cateGoryService.index());
    }

    @RequestMapping("current")
    public BaseReqVo current(Integer id){
        return BaseReqVo.ok(cateGoryService.current(id));
    }

}
