package com.jiaxingrong.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Pic {

    /**
     * size : 457734
     * addTime : 2019-12-24 19:49:31
     * name : ChMlWV27vPaIM0-zACJXyAtVPZQAANGEAHFPicAIlfg368.jpg
     * updateTime : 2019-12-24 19:49:31
     * id : 3
     * type : image/jpeg
     * key : s6ect3m2l7utxbaikxva.jpg
     * url : http://192.168.2.100:8081/wx/storage/fetch/s6ect3m2l7utxbaikxva.jpg
     */
    private Long size;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date addTime;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private Integer id;
    private String type;
    private String key;
    private String url;

}
