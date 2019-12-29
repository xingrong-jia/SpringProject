package com.jiaxingrong.controller.wx;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @RequestMapping("index")
    public BaseReqVo index(){
        return BaseReqVo.ok(homeService.index());
    }
}
