package com.jiaxingrong.service;

import com.jiaxingrong.model.Ad;
import com.jiaxingrong.model.Laypage;

import java.util.Map;

public interface AdService {
    Map<String, Object> list(Laypage laypage);

    Ad addAd(Ad ad);

    Ad updateAd(Ad ad);

    Ad deleteAd(Ad ad);
}
