package com.jiaxingrong.service;

import com.jiaxingrong.model.Pic;
import org.springframework.web.multipart.MultipartFile;

public interface PicService {
    Pic storage(MultipartFile file);
}
