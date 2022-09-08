package com.lhq.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhq.mybatis.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
UserMapper 通过继承BaseMapper来实现crud操作,泛型是你具体操作的表

MybatisPlus的框架结构
先扫描实体   -> 通过反射抽取实体类    -> 然后分析要操作的表是那张,然后对应的属性是表的那个字段  -> 然后生成相对应的sql
->  然后注入到mybatis的容器中
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    // 自定义查询,根据name和age进行查询
    List<User> queryUserByNameAndAge(@Param("name") String name, @Param("age") Integer age);

    // 自定义分页功能,通过年龄查询用户信息,并分页,分页插件,必须位于第一个参数
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
