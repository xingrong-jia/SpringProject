package com.jiaxingrong.controller.wx;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.BrandService;
import com.jiaxingrong.service.CateGoryService;
import com.jiaxingrong.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    CateGoryService cateGoryService;


    @RequestMapping("count")
    public BaseReqVo count() {
        return BaseReqVo.ok(goodsService.count());
    }

    @RequestMapping("category")
    public BaseReqVo category(Integer id) {
        return BaseReqVo.ok(cateGoryService.category(id));
    }

    @RequestMapping("list")
    public BaseReqVo list(Laypage laypage) {
        return BaseReqVo.ok(goodsService.wxList(laypage));
    }

    @RequestMapping("detail")
    public BaseReqVo detail(Integer id) {
        return BaseReqVo.ok(goodsService.detail(id));
    }

    @RequestMapping("related")
    public BaseReqVo related(Integer id) {
        return BaseReqVo.ok(goodsService.related(id));
    }


}

