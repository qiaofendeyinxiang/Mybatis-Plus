package com.lhq.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lhq.mybatis.bean.User;
import com.lhq.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    // 组装查询条件
    @Test
    void test1() {
        // 查询用户名包含a,年龄在20-30之间,邮箱信息不为null
        /*
==>  Preparing: SELECT uid AS id,user_name AS name,age,email,is_delete FROM tbl_user WHERE is_delete=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
==> Parameters: %a%(String), 20(Integer), 30(Integer)
         */
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a").between("age", 20, 30).isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    void test2() {
//SELECT uid AS id,user_name AS name,age,email,is_delete FROM tbl_user WHERE is_delete=0 ORDER BY age DESC,id ASC
        // 查询用户信息,根据用户年龄降序排序,若年龄相同,则id升序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }

    // 组装删除条件
    @Test
    void test3() {
        // UPDATE tbl_user SET is_delete=1 WHERE is_delete=0 AND (email IS NULL)
        // 删除用户信息为null的信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int i = userMapper.delete(queryWrapper);
        System.out.println(i);
    }

    // 组装修改条件
    @Test
    void test4() {
        /*
UPDATE tbl_user SET user_name=?, age=?, email=? WHERE is_delete=0 AND (age > ? AND user_name LIKE ? OR email IS NULL)
==> Parameters: rose(String), 20(Integer), rose@qq.com(String), 15(Integer), %a%(String)
         */
        // 将年龄大于15并且用户名包含a,或邮箱为null的进行修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 15).like("user_name","a").or().isNull("email");
        // 修改的内容, 修改的条件
        int rs = userMapper.update(new User(null, "rose", 20, "rose@qq.com", 0), queryWrapper);
        System.out.println(rs);
    }

    // 条件优先级
    @Test
    void test5() {
        /*
        ==>  Preparing: UPDATE t_user SET user_name=?, age=?, email=? WHERE isDelete=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
        ==> Parameters: King(String), 24(Integer), king@qq.com(String), %o%(String), 15(Integer)
         */
        // 用户名包含有a, 并且年龄大于20或邮箱为null的用户信息修改
        // 那么大表达式的条件优先执行
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "o").and(i->i.gt("age",15).or().isNull("email"));
        int rs = userMapper.update(new User(null, "King", 24, "king@qq.com", 0), queryWrapper);
        System.out.println(rs);
    }

    // 组装select语句
    @Test
    void test6() {
        // 查询用户名,年龄,邮箱的信息
        // SELECT user_name,age,email FROM t_user WHERE isDelete=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 查询指定的字段
        queryWrapper.select("user_name", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }

    // 组装子查询
    @Test
    void test7() {
        // ==>  Preparing: SELECT uid AS id,user_name AS name,age,email,isDelete FROM t_user WHERE isDelete=0 AND (uid IN (SELECT uid FROM t_user WHERE uid <= 100))
        // 查询id小于等于100的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid", "SELECT uid FROM tbl_user WHERE uid <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }


    @Test
    void test8() {
        // 用户名包含有k, 并且年龄大于20或邮箱为null的用户信息修改
        // 那么大表达式的条件优先执行
        /*
UPDATE tbl_user SET user_name=?,email=? WHERE is_delete=0 AND (user_name LIKE ? AND (age > ? OR email IS NULL))
==> Parameters: lucy(String), lucy@qq.com(String), %k%(String), 20(Integer)
         */
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("user_name", "k").and(i->i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("user_name", "lucy").set("email", "lucy@qq.com");
        int i = userMapper.update(null, updateWrapper);
        System.out.println(i);
    }

    // 模拟开发组装条件
    @Test
    void test9() {
        /*
        ==>  Preparing: SELECT uid AS id,user_name AS name,age,email,isDelete FROM t_user WHERE isDelete=0 AND (user_name LIKE ? AND age >= ? AND age <= ?)
        ==> Parameters: %l%(String), 20(Integer), 30(Integer)
         */
        String name = "l";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 判断某个字符串不为Null, "", 空白符就是"            "
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("user_name", name);
        }

        if (ageBegin != null) {
            // ge 大于等于
            queryWrapper.ge("age", ageBegin);
        }

        if (ageEnd != null) {
            // le 小于等于
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }

    // 模拟开发组装条件 简化版
    @Test
    void test10() {
        /*
SELECT uid AS id,user_name AS name,age,email,is_delete FROM tbl_user WHERE is_delete=0 AND (user_name LIKE ? AND age >= ? AND age <= ?)
==> Parameters: %l%(String), 20(Integer), 30(Integer)
         */
        String name = "l";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", name).ge(ageBegin != null, "age", ageBegin).le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }

/*
SELECT uid AS id,user_name AS name,age,email,is_delete FROM tbl_user WHERE is_delete=0 AND (user_name LIKE ? AND age >= ? AND age <= ?)
==> Parameters: %l%(String), 20(Integer), 30(Integer)
 */
    @Test
    void test11() {
        String name = "l";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(name), User::getName, name).ge(ageBegin != null, User::getAge, ageBegin).le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = userMapper.selectList(lambdaQueryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    void test12() {
        // 用户名包含有k, 并且年龄大于20或邮箱为null的用户信息修改
        // 那么大表达式的条件优先执行
        // ==>  Preparing: UPDATE t_user SET user_name=?,age=?,email=? WHERE isDelete=0 AND (user_name LIKE ? AND age >= ? OR email IS NULL)
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.like(User::getName, "y").ge(User::getAge, 20).or().isNull(User::getEmail);
        // 修改成的内容
        lambdaUpdateWrapper.set(User::getName, "小王abel").set(User::getAge, 28).set(User::getEmail, "wang@qq.com");
        int i = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println(i);
    }
}
