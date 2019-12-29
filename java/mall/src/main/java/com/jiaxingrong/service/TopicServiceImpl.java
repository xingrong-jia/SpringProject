package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.TopicMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicMapper topicMapper;

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        TopicExample topicExample = new TopicExample();
        topicExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        TopicExample.Criteria criteria = topicExample.createCriteria();
        if (StringTool.isNotNull(laypage.getTitle())) {
            criteria.andTitleLike("%"+laypage.getTitle()+"%");
        }
        if (StringTool.isNotNull(laypage.getSubtitle())) {
            criteria.andSubtitleLike("%"+laypage.getSubtitle()+"%");
        }
        criteria.andDeletedEqualTo(false);
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        long total = topicPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", topics);
        return map;
    }

    @Override
    public Topic addTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        topic.setAddTime(new Date());
        topic.setDeleted(false);
        topicMapper.insertSelective(topic);
        return topic;
    }

    @Override
    public Topic updateTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        topicMapper.updateByPrimaryKeySelective(topic);
        return topic;
    }

    @Override
    public int deleteTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        topic.setDeleted(true);
        return topicMapper.updateByPrimaryKeySelective(topic);
    }
}
