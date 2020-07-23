package com.wj.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wj.manager.business.mapper")
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(new Class[]{ManagerApplication.class}, args);
    }
}
