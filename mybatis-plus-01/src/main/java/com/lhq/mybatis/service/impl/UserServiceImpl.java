package com.lhq.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhq.mybatis.bean.User;
import com.lhq.mybatis.mapper.UserMapper;
import com.lhq.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/*
    自定义实现通用Service, ServiceImpl<UserMapper, User> 操作的接口和实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
