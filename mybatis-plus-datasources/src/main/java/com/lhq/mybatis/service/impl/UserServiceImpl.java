package com.lhq.mybatis.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhq.mybatis.bean.User;
import com.lhq.mybatis.mapper.UserMapper;
import com.lhq.mybatis.service.UserService;
import org.springframework.stereotype.Service;

@Service
@DS("master")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
