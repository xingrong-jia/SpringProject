package com.stylefeng.guns.rest.expection;

import lombok.Data;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-14 16:40
 */
@Data
public class MysqlExpection extends Exception{

    private String msg;


    public MysqlExpection() {
    }

    public MysqlExpection(String message) {
        this.msg=message;
    }
}
