package com.jiaxingrong.service;

import com.jiaxingrong.model.CateGory;

import java.util.List;
import java.util.Map;

public interface CateGoryService {
    List<CateGory> list();

    List<Map> listL1();

    CateGory addCateGory(CateGory cateGory);

    int updateCateGory(CateGory cateGory);

    int deleteCateGory(CateGory cateGory);
}
