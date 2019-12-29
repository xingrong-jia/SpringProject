package com.jiaxingrong.controller.admin;

import com.jiaxingrong.model.BaseReqVo;
import com.jiaxingrong.model.Brand;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.service.BrandService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("list")
    @RequiresPermissions("admin:brand:list")
    public BaseReqVo<Map> brandList(Laypage laypage){
        Map<String,Object> map = brandService.list(laypage);
        return new BaseReqVo<>(map,"成功",0);
    }



    @RequestMapping("create")
    @RequiresPermissions("admin:brand:create")
    public BaseReqVo addBrand(@RequestBody Brand brand){
        brand = brandService.addBrand(brand);
        return new BaseReqVo(brand,"成功",0);
    }

    @RequestMapping("update")
    @RequiresPermissions("admin:brand:update")
    public BaseReqVo updateBrand(@RequestBody Brand brand){
        int i = brandService.updateBrand(brand);
        return new BaseReqVo(brand,"成功",0);
    }

    @RequestMapping("delete")
    @RequiresPermissions("admin:brand:delete")
    public BaseReqVo deleteBrand(@RequestBody Brand brand){
        int i = brandService.deleteBrand(brand);
        return new BaseReqVo(null,"成功",0);
    }
}
