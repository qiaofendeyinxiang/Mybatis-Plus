package com.lhq.mybatis;

import com.lhq.mybatis.service.ProductService;
import com.lhq.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
    测试从多个数据源中查询数据
 */
@SpringBootTest
class MybatisPlusDatasourcesApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    void test() {
        /*
        User(id=1, name=rose, age=24, email=rose@qq.com)
        Product(id=1, name=外星人笔记本, price=100, version=0)
         */
        System.out.println(userService.getById(1L));
        System.out.println(productService.getById(1L));
    }

    @Test
    void contextLoads() {
    }

}
