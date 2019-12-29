package com.jiaxingrong.service;

import com.jiaxingrong.model.Laypage;
import com.jiaxingrong.model.Storage;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface StorageService {
    Storage storage(MultipartFile file);

    Map list(Laypage laypage);

    Storage updateTopic(Storage storage);

    int deleteTopic(Storage storage);
}
