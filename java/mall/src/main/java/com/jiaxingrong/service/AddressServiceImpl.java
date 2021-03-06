package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.AddressMapper;
import com.jiaxingrong.mapper.RegionMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
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
        AddressExample.Criteria criteria = addressExample.createCriteria();
        if (StringTool.isNotNull(laypage.getName())) {
            criteria.andNameLike("%" + laypage.getName() + "%");
        }
        if (laypage.getUserId() != null) {
            criteria.andUserIdEqualTo(laypage.getUserId());
        }
        criteria.andDeletedEqualTo(false);
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
