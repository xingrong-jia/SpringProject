package com.jiaxingrong.service;

import com.jiaxingrong.model.Brand;
import com.jiaxingrong.model.Laypage;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Map<String, Object> list(Laypage laypage);

    Brand addBrand(Brand brand);

    int updateBrand(Brand brand);

    int deleteBrand(Brand brand);


    List<Brand> selectBrands();

    Map detail(Integer id);
}
