package com.lhq.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/*
    测试MybatisPlus的自动代码生成器
 */
public class Mgb {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:4406/mybatis_plus?characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "123456789";
        FastAutoGenerator.create(url, username, password).globalConfig(builder -> {
            builder.author("lhq").fileOverride().outputDir("D:\\MybatisPlus-mgb");
        }).packageConfig(builder -> {
            builder.parent("com.lhq").moduleName("mybatisplus").pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\MybatisPlus-mgb"));
        }).strategyConfig(builder -> {
            builder.addInclude("tbl_user").addTablePrefix("tbl_", "c_");
        }).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
}
