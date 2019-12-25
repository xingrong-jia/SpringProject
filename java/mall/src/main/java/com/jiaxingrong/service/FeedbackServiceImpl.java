package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.FeedbackMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {


    @Autowired
    FeedbackMapper feedbackMapper;

    /**
     * 根据分页情况查询响应的数据
     *
     * @param laypage
     * @return
     */
    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.setOrderByClause(laypage.getSort() + " " + laypage.getOrder());
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        if (StringTool.isNotNull(laypage.getUsername())) {
            criteria.andUsernameLike("%" + laypage.getUsername() + "%");
        }
        if (laypage.getId() != null) {
            criteria.andIdEqualTo(laypage.getId());
        }
        criteria.andDeletedEqualTo(false);
        List<Feedback> feedbacks = feedbackMapper.selectByExample(feedbackExample);
        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(feedbacks);
        long total = feedbackPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", feedbacks);
        return map;
    }
}
