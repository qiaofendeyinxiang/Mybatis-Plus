package com.lhq.mybatis;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhq.mybatis.bean.Product;
import com.lhq.mybatis.bean.User;
import com.lhq.mybatis.mapper.ProductMapper;
import com.lhq.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
    测试MybatisPlus分页插件
 */
@SpringBootTest
public class PageTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    void page() {
        // 查询第一页,一共3跳记录
        Page<User> page = userMapper.selectPage(new Page<User>(1, 3), null);
        // page里面封装了所有的信息
        System.out.println(page);
    }

    // 测试自定义分页功能
    @Test
    void selectPageVo() {
        Page<User> page = new Page<>(1, 3);
        userMapper.selectPageVo(page, 20);
        System.out.println(page);
    }

    // 测试乐观锁插件
    @Test
    void testProduct() {
        Product productLi = productMapper.selectById(1L);
        System.out.println("小李查询的商品价格 : " + productLi.getPrice());
        Product productWang = productMapper.selectById(1L);
        System.out.println("小王查询的商品价格 : " + productWang.getPrice());
        //小李将商品+50
        productLi.setPrice(productLi.getPrice() + 50);
        //跟新到数据库
        productMapper.updateById(productLi);
        //小王将商品-30
        productWang.setPrice(productWang.getPrice() - 30);
        //跟新到数据库
        int rs = productMapper.updateById(productWang);
        if (rs == 0) {
            // 操作失败,
            Product productNew = productMapper.selectById(1L);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }
        //老板查询商品价格
        Product productLaoBan = productMapper.selectById(1L);
        System.out.println("老板查询的商品价格 : " + productLaoBan.getPrice());
    }
}
