package com.jiaxingrong.model;

import lombok.Data;

import java.util.List;

@Data
public class Region {
    private Integer id;

    private Integer pid;

    private String name;

    private Byte type;

    private Integer code;

    private List<Region> children;
}