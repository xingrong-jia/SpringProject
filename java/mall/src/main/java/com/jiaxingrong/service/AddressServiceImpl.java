package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.AddressMapper;
import com.jiaxingrong.mapper.RegionMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    RegionMapper regionMapper;

    /**
     * 根据分页情况查询响应的数据
     *
     * @param laypage
     * @return
     */
    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        AddressExample addressExample = new AddressExample();
        addressExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        if (laypage.getName() != null) {
            addressExample.createCriteria().andNameLike("%" + laypage.getUsername() + "%");
        }
        if (laypage.getUserId() != null) {
            addressExample.createCriteria().andUserIdEqualTo(laypage.getUserId());
        }
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        for (Address address : addresses) {
            RegionExample regionExample = new RegionExample();
            ArrayList<Integer> list = new ArrayList<>();
            list.add(address.getProvinceId());
            list.add(address.getCityId());
            list.add(address.getAreaId());
            regionExample.createCriteria().andCodeIn(list);
            List<Region> regions = regionMapper.selectByExample(regionExample);
            String s = "";
            for (Region region : regions) {
                s += region.getName();
            }
            address.setAddress(s + address.getAddress());
        }
        PageInfo<Address> addressPageInfo = new PageInfo<>(addresses);
        long total = addressPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", addresses);
        return map;
    }
}
