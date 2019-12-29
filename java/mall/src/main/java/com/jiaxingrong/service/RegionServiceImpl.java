package com.jiaxingrong.service;

import com.jiaxingrong.mapper.RegionMapper;
import com.jiaxingrong.model.Region;
import com.jiaxingrong.model.RegionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    /**
     * 查询所有城市列表
     * @return
     */
    @Override
    public List<Region> list() {
        RegionExample example = new RegionExample();
        example.createCriteria().andTypeEqualTo((byte) 1);
        List<Region> regions = regionMapper.selectByExample(example);
        for (Region region : regions) {
            RegionExample example1 = new RegionExample();
            RegionExample.Criteria criteria = example1.createCriteria();
            criteria.andCodeBetween(region.getCode()*100,region.getCode()*100+99);

            criteria.andTypeEqualTo((byte) 2);
            List<Region> regions1 = regionMapper.selectByExample(example1);
            for (Region region1 : regions1) {
                RegionExample example2 = new RegionExample();
                RegionExample.Criteria criteria1 = example2.createCriteria();
                criteria1.andCodeBetween(region1.getCode()*100,region1.getCode()*100+99);
                criteria1.andTypeEqualTo((byte) 3);
                List<Region> regions2 = regionMapper.selectByExample(example2);
                region1.setChildren(regions2);
            }
            region.setChildren(regions1);
        }
        return regions;
    }
}
