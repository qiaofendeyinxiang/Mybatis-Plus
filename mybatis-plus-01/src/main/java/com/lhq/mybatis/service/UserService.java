package com.lhq.mybatis.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lhq.mybatis.bean.User;

/*
通用Service CRUD 封装IService接口,进一步封装CRUD采用get查询单行,remove删除,list查询集合,page分页,前缀命名方式区分Mapper层避免混淆

泛型 T 为任意实体对象,也就是你操作的表

建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承
Mybatis-Plus 提供的基类
 */
public interface UserService extends IService<User> {
}
