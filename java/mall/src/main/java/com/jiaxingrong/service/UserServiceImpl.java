package com.jiaxingrong.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.UserMapper;
import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.User;
import com.jiaxingrong.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 根据分页情况查询响应的数据
     * @param laypage
     * @return
     */
    @Override
    public Map<String, Object> list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(),laypage.getLimit());
        UserExample userExample = new UserExample();
        userExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        if (laypage.getUsername()!=null){
            userExample.createCriteria().andUsernameLike("%"+laypage.getUsername()+"%");
        }
        if (laypage.getMobile()!=null){
            userExample.createCriteria().andMobileLike("%"+laypage.getMobile()+"%");
        }
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",users);
        return map;
    }
}
