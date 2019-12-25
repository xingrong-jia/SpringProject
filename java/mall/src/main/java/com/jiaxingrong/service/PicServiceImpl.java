package com.jiaxingrong.service;

import com.jiaxingrong.mapper.PicMapper;
import com.jiaxingrong.model.Pic;
import com.jiaxingrong.utils.DateUtils;
import com.jiaxingrong.utils.StringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
public class PicServiceImpl implements PicService {

    @Autowired
    PicMapper picMapper;

    @Value("D://File/Pic/")
    String picFile;

    @Value("http://localhost:8080")
    String url;

    /**
     * 保存图片的逻辑
     *
     * @param file
     * @return
     */
    @Override
    public Pic storage(MultipartFile file) {
        Pic pic = multipartFile(file);
        pic.setName(file.getOriginalFilename());
        pic.setType(file.getContentType());
        pic.setSize(file.getSize());
        pic.setAddTime(new Date());
        pic.setUpdateTime(new Date());
        int i = picMapper.insertPic(pic);
        if (i!=1) return null;
        return pic;
    }

    private Pic multipartFile(MultipartFile file) {
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
        Pic pic = new Pic();
        pic.setKey(hashFile);
        pic.setUrl(url+hashFile);
        return pic;
    }

}
