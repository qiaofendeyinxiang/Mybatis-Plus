package com.lhq.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lhq.mybatis.mapper")
public class MybatisPlusDatasourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDatasourcesApplication.class, args);
    }

}
