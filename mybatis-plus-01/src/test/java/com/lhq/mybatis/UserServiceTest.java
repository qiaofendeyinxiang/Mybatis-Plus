package com.lhq.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lhq.mybatis.bean.User;
import com.lhq.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    测试MybatisPlus封装的通用service操作
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    // 测试逻辑删除
    @Test
    void delete() {
        // UPDATE tbl_user SET is_delete=1 WHERE uid=? AND is_delete=0
        // 删除语句变成修改语句
        boolean rs = userService.removeById(6L);
        System.out.println(rs);
    }

    // 添加
    @Test
    void save() {
        // 添加单个
        /*INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        boolean abel = userService.save(new User(null, "abel", 30, "abel@qq.com"));
        System.out.println(abel);*/

        // 批量添加
        /*
        INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        List<User> list = new ArrayList<>();
        list.add(new User(null, "rose", 35, "rose@qq.com"));
        list.add(new User(null, "king", 30, "king@qq.com"));
        boolean rs = userService.saveBatch(list);
        System.out.println(rs);
         */

        List<User> list = new ArrayList<>();
        list.add(new User(null, "rose", 35, "rose@qq.com"));
        list.add(new User(null, "king", 30, "king@qq.com"));
        boolean rs = userService.saveBatch(list,3);
        System.out.println(rs);
    }

    // 删除
    @Test
    void remove() {
        /*DELETE FROM user WHERE id=?
        boolean rs = userService.removeById(1567410027344482306L);
        System.out.println(rs);
         */

        /*
        DELETE FROM user WHERE id IN ( ? , ? )
        List<Long> ids = new ArrayList<>();
        ids.add(1567410027239624705L);
        ids.add(1567409898650652673L);
        boolean rs = userService.removeByIds(ids);
        System.out.println(rs);
         */

        // DELETE FROM user WHERE name = ? AND id = ?
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1567409898508046338L);
        map.put("name", "rose");
        boolean rs = userService.removeByMap(map);
        System.out.println(rs);
    }

    // 改
    @Test
    void update() {
        // UPDATE user SET name=?, age=?, email=? WHERE id=?
        List<User> list = new ArrayList<>();
        list.add(new User(1567409467153186817L, "King", 23, "ki@qq.com"));
        list.add(new User(1567409467039940609L, "Rose", null, null));
        boolean rs = userService.updateBatchById(list);
        System.out.println(rs);
    }

    @Test
    void get() {
        User user = userService.getById(1L);
        System.out.println(user);
    }

    @Test
    void count() {
        int count = userService.count();
        System.out.println(count);
    }
}
