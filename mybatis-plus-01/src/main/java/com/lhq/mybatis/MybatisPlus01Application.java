package com.lhq.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描接口所在的包
@MapperScan("com.lhq.mybatis.mapper")
public class MybatisPlus01Application {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlus01Application.class, args);
    }

}
