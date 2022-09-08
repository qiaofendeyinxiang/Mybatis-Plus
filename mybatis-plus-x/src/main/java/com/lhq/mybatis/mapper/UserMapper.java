package com.lhq.mybatis.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.ibatis.annotations.Param;

import com.lhq.mybatis.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
* @author shuiye
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-09-07 22:33:26
* @Entity com.lhq.mybatis.bean.User
*/
public interface UserMapper extends BaseMapper<User> {
    int insertSelective(User user);

    int insertAll(User user);

    @Override
    int deleteById(Serializable id);

    int updateIdAndAge(@Param("id") Long id, @Param("age") Integer age);

    List<User> selectAll();

    @Override
    Integer selectCount(Wrapper<User> queryWrapper);
}




