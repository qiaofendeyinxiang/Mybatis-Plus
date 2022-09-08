package com.lhq.mybatis.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhq.mybatis.bean.Product;
import com.lhq.mybatis.mapper.ProductMapper;
import com.lhq.mybatis.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@DS("slave_1")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
