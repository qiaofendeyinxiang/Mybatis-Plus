package com.lhq.mybatis;

import com.lhq.mybatis.bean.User;
import com.lhq.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    测试BaseMapper相关的功能
 */
@SpringBootTest
class MybatisPlus01ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    // 添加操作
    @Test
    void add() {
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        int i = userMapper.insert(new User(null, "lucy", 25, "lucy@qq,com"));
        System.out.println(i);
    }

    @Test
    void delete() {
        /*
        根据id删除
        DELETE FROM user WHERE id=?
        int i = userMapper.deleteById(1567400389085552641L);
        System.out.println(i);
         */


        /*
        根据id进行批量删除
        DELETE FROM user WHERE id IN ( ? , ? )
        List<Long> ids = new ArrayList<>();
        ids.add(1567400928112291842L);
        ids.add(1567400982277558274L);
        int i = userMapper.deleteBatchIds(ids);
        System.out.println(i);
        */

        // 使用map集合进行删除
        // DELETE FROM user WHERE name = ? AND id = ?
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1567401656469991425L);
        map.put("name", "lucy");
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }


    @Test
    void update() {
        // 根据id进行修改
        int i = userMapper.updateById(new User(5L, "lucy", null, null));
        System.out.println(i);
    }

    @Test
    void select() {
        // SELECT id,name,age,email FROM user
        // 通过条件构造器queryWrapper来查询一个list集合,若没有条件,则传入null
       /* List<User> list = userMapper.selectList(null);
        for (User user : list) {
            System.out.println(user);
        }*/

        /*
        根据id进行查询
        SELECT id,name,age,email FROM user WHERE id=?
        User user = userMapper.selectById(1L);
        System.out.println(user);
         */

        /*
        根据id进行批量查询
        SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<Long> l = new ArrayList<>();
        l.add(1L);
        l.add(2L);
        l.add(3L);
        List<User> users = userMapper.selectBatchIds(l);
        for (User u : users) {
            System.out.println(u);
        }
         */

      /*
        使用map进行查询
        SELECT id,name,age,email FROM user WHERE name = ? AND id = ?
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1L);
        map.put("name", "Jone");
        List<User> list = userMapper.selectByMap(map);
        for (User user : list) {
            System.out.println(user);
        }
       */
    }

    @Test
    public void queryUserByNameAndAge() {
        // 自定义查询
        // select * from user where name = ? or age = ?
        List<User> list = userMapper.queryUserByNameAndAge("Jone", 20);
        for (User user : list) {
            System.out.println(user);
        }
    }


    @Test
    void contextLoads() {
    }

}
