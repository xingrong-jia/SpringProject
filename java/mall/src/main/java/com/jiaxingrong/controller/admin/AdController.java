package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.Ad;
import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/ad")
public class AdController {

    @Autowired
    AdService adService;

    @RequestMapping("list")
    public BaseReqVo<Map> addList(Laypage laypage) {
        Map<String,Object> map = adService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }

    @RequestMapping("create")
    public BaseReqVo<Map> create(@RequestBody Ad ad) {
        ad = adService.addAd(ad);
        return BaseReqVo.ok(ad);
    }

    @RequestMapping("update")
    public BaseReqVo<Map> update(@RequestBody Ad ad) {
        ad = adService.updateAd(ad);
        return BaseReqVo.ok(ad);
    }

    @RequestMapping("delete")
    public BaseReqVo<Map> delete(@RequestBody Ad ad) {
        ad = adService.deleteAd(ad);
        return BaseReqVo.ok();
    }
}
