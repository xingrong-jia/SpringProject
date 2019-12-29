package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.Search_historyMapper;
import com.jiaxingrong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Search_historyServiceImpl implements Search_historyService {


    @Autowired
    Search_historyMapper search_historyMapper;

    /**
     * 根据分页情况查询响应的数据
     *
     * @param laypage
     * @return
     */
    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        Search_historyExample search_historyExample = new Search_historyExample();
        search_historyExample.setOrderByClause(laypage.getSort() + " " + laypage.getOrder());
        Search_historyExample.Criteria criteria = search_historyExample.createCriteria();
        if (laypage.getKeyword()!= null) {
            criteria.andKeywordLike("%" + laypage.getKeyword() + "%");
        }
        if (laypage.getUserId() != null) {
            criteria.andUserIdEqualTo(laypage.getUserId());
        }
        criteria.andDeletedEqualTo(false);
        List<Search_history> search_histories = search_historyMapper.selectByExample(search_historyExample);
        PageInfo<Search_history> search_historyPageInfo = new PageInfo<>(search_histories);
        long total = search_historyPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", search_histories);
        return map;
    }
}
