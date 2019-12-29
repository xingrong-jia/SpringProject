package com.jiaxingrong.controller.wx;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/search")
public class WxSearchController {

    @Autowired
    KeywordService keywordService;

    @RequestMapping("index")
    public BaseReqVo index(){
        return BaseReqVo.ok(keywordService.index());
    }
}
