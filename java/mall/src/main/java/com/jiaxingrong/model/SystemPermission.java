package com.jiaxingrong.model;

import lombok.Data;

import java.util.List;

@Data
public class SystemPermission {
    private Integer primaryId;

    private String id;

    private String label;

    private Integer pid;

    private String api;

    List<SystemPermission> children;
}