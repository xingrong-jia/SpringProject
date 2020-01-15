package com.stylefeng.guns;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.stylefeng.guns"})
@EnableDubboConfiguration
public class GunsMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsMqApplication.class, args);
    }
}
