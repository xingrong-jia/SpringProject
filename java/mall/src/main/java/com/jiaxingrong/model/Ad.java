package com.jiaxingrong.model;

import lombok.Data;

import java.util.Date;

@Data
public class Ad {
    private Integer id;

    private String name;

    private String link;

    private String url;

    private Byte position;

    private String content;

    private Date startTime;

    private Date endTime;

    private Boolean enabled;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;


}