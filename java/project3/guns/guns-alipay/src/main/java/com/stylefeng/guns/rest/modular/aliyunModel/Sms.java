package com.stylefeng.guns.rest.modular.aliyunModel;

import lombok.Data;

@Data
public class Sms {
    private String signName;
    private String templateCode;

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
