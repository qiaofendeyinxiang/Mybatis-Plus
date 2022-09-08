package com.lhq.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhq.mybatis.bean.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMapper extends BaseMapper<Person> {
}
