package com.lhq.mybatis.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
/*
经过以上的测试，在使用MyBatis-Plus实现基本的CRUD时，我们并没有指定要操作的表，只是在
Mapper接口继承BaseMapper时，设置了泛型User，而操作的表为user表

由此得出结论，MyBatis-Plus在确定操作的表时，由BaseMapper的泛型决定，即实体类型决
定，且默认操作的表名和实体类型的类名一致

@TableName("tbl_user")  指定映射数据库中的那张表
 */
// @TableName("tbl_user")
public class User {
    /*
    为什么主键使用Long类型,而不是Integer类型?
        因为mybatis-plus默认的主键生成策略是使用雪花算法进行生成的,而Integer的范围不够

    @TableId("uid") 指定使用uid作为主键,因为MybatisPlus默认是将id作为主键,当主键发生
    变化,需要通过@TableId注解来指定你是使用那个属性作为主键

    String value() default "";          指定使用那个属性作为主键
    IdType type() default IdType.NONE;  设置是自增还是雪花算法作为主键,前提是表要有自动递增

    AUTO(0) 自增
    ASSIGN_ID(3) 雪花算法

    如果你是雪花算法生成的id,如果手动传入id,那么就不会使用雪花生成的id
     */
    //@TableId(value = "uid", type = IdType.AUTO)
    @TableId("uid")
    private Long id;
    /*
    @TableField("user_name")    指定将name属性映射为数据库的user_name字段

    如果数据库是带有下划线的字段,那么MybatisPlus默认开启驼峰命名
    例如: user_name               userName        我这里是为了用而用
     */
    @TableField("user_name")
    private String name;
    private Integer age;
    private String email;
    /*
    @TableLogic 逻辑删除
    物理删除：真实删除，将对应数据从数据库中删除，之后查询不到此条被删除的数据

    逻辑删除：假删除，将对应数据中代表是否被删除字段的状态修改为“被删除状态”，之后在数据库
    中仍旧能看到此条数据记录
    使用场景：可以进行数据恢复
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;

    public User(Long id, String name, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
