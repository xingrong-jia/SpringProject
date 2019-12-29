package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.LogMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        LogExample logExample = new LogExample();
        logExample.setOrderByClause(laypage.getSort() + " " + laypage.getOrder());
        LogExample.Criteria criteria = logExample.createCriteria();
        if (StringTool.isNotNull(laypage.getName())) {
            criteria.andAdminLike("%" + laypage.getName() + "%");
        }
        criteria.andDeletedEqualTo(false);
        List<Log> logs = logMapper.selectByExample(logExample);
        PageInfo<Log> logPageInfo = new PageInfo<>(logs);
        long total = logPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", logs);
        return map;
    }
}
