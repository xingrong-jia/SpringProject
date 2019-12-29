package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.CommentMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        CommentExample.Criteria criteria = commentExample.createCriteria();
        if (laypage.getUserId()!=null) {
            criteria.andUserIdEqualTo(laypage.getUserId());
        }
        if (laypage.getValueId()!=null) {
            criteria.andValueIdEqualTo(laypage.getValueId());
        }
        criteria.andDeletedEqualTo(false);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(comments);
        long total = commentPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", comments);
        return map;
    }

    @Override
    public boolean reply(Comment comment) {
        Comment comment1 = commentMapper.selectByPrimaryKey(comment.getCommentId());
        if (comment1.getType()!=0) {
            comment.setType((byte) 0);
            int update = commentMapper.updateByPrimaryKeySelective(comment);
            if (update==1) return true;
        }
        return false;
    }

    @Override
    public boolean deleteComment(Comment comment) {
        comment.setUpdateTime(new Date());
        comment.setDeleted(true);
        int update = commentMapper.updateByPrimaryKeySelective(comment);
        if (update==1) return true;
        return false;
    }
}
