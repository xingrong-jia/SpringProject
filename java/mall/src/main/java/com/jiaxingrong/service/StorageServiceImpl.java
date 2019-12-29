package com.jiaxingrong.service;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiaxingrong.mapper.StorageMapper;
import com.jiaxingrong.model.*;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Value("D://File/Pic/")
    String picFile;

    @Value("http://localhost:8080/Pic")
    String url;

    /**
     * 保存图片的逻辑
     *
     * @param file
     * @return
     */
    @Override
    public Storage storage(MultipartFile file) {
        Storage storage = multipartFile(file);
        storage.setName(file.getOriginalFilename());
        storage.setType(file.getContentType());
        storage.setSize((int) file.getSize());
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setDeleted(false);
        int i = storageMapper.insertSelective(storage);
        if (i!=1) return null;
        return storage;
    }

    @Override
    public Map list(Laypage laypage) {
        PageHelper.startPage(laypage.getPage(), laypage.getLimit());
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(laypage.getSort()+" "+laypage.getOrder());
        StorageExample.Criteria criteria = storageExample.createCriteria();
        if (StringTool.isNotNull(laypage.getKey())) {
            criteria.andKeyEqualTo(laypage.getKey());
        }
        if (StringTool.isNotNull(laypage.getName())) {
            criteria.andNameEqualTo(laypage.getName());
        }
        criteria.andDeletedEqualTo(false);
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        PageInfo<Storage> storagePageInfo = new PageInfo<>(storages);
        long total = storagePageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", storages);
        return map;
    }

    @Override
    public Storage updateTopic(Storage storage) {
        storage.setUpdateTime(new Date());
        storageMapper.updateByPrimaryKeySelective(storage);
        return storage;
    }

    @Override
    public int deleteTopic(Storage storage) {
        storage.setUpdateTime(new Date());
        storage.setDeleted(true);
        return storageMapper.updateByPrimaryKeySelective(storage);
    }

    private Storage multipartFile(MultipartFile file) {
        String hashFile = StringTool.hashFile(file.getOriginalFilename());
        String filePath = picFile + hashFile;

        File file1 = new File(filePath);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Storage storage = new Storage();
        storage.setKey(hashFile);
        storage.setUrl(url+hashFile);
        return storage;
    }

}
