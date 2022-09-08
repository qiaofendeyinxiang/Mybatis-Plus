package com.lhq.mybatis;

import com.lhq.mybatis.bean.Person;
import com.lhq.mybatis.enums.SexEnums;
import com.lhq.mybatis.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
    测试添加数据,类的属性是枚举
 */
@SpringBootTest
public class EnumsTest {
    @Autowired
    private PersonMapper personMapper;

    @Test
    void add() {
        int i = personMapper.insert(new Person(null, "lucy", SexEnums.FEMALE));
        System.out.println(i);
    }
}
