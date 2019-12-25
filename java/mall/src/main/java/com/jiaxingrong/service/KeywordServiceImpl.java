package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.KeywordMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        KeywordExample keywordExample = new KeywordExample();
        keywordExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        if (StringTool.isNotNull(laypage.getKeyword())) {
            criteria.andKeywordLike("%" + laypage.getKeyword() + "%");
        }
        if (StringTool.isNotNull(laypage.getUrl())) {
            criteria.andUrlLike("%" + laypage.getUrl() + "%");
        }
        criteria.andDeletedEqualTo(false);
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        PageInfo<Keyword> keywordPageInfo = new PageInfo<>(keywords);
        long total = keywordPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", keywords);
        return map;
    }

    @Override
    public Keyword addKeyword(Keyword keyword) {
        keyword.setSortOrder(100);
        keyword.setAddTime(new Date());
        keyword.setUpdateTime(new Date());
        keyword.setDeleted(false);
        keywordMapper.insertSelective(keyword);
        return keyword;
    }

    @Override
    public Keyword updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        keywordMapper.updateByPrimaryKeySelective(keyword);
        return keyword;
    }

    @Override
    public Keyword deleteKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        keyword.setDeleted(true);
        keywordMapper.updateByPrimaryKeySelective(keyword);
        return keyword;
    }
}
