package com.lhq.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhq.mybatis.bean.Person;
import com.lhq.mybatis.service.PersonService;
import com.lhq.mybatis.mapper.PersonMapper;
import org.springframework.stereotype.Service;

/**
* @author shuiye
* @description 针对表【tbl_person】的数据库操作Service实现
* @createDate 2022-09-07 22:36:09
*/
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person>
    implements PersonService{

}




