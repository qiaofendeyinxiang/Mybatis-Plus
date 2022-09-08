package com.lhq.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhq.mybatis.bean.User;
import com.lhq.mybatis.service.UserService;
import com.lhq.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author shuiye
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-09-07 22:33:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




