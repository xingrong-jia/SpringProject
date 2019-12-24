package com.jiaxingrong.model;

import lombok.Data;

@Data
public class AdminPassword {

    String oldPassword;

    String newPassword;

    String newPassword2;
}