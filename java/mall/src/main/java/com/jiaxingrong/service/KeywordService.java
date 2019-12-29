package com.jiaxingrong.service;

import com.jiaxingrong.model.Keyword;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface KeywordService {
    Map<String, Object> list(Laypage lazypage);

    Keyword addKeyword(Keyword keyword);

    Keyword updateKeyword(Keyword keyword);

    Keyword deleteKeyword(Keyword keyword);

    Map index();
}
