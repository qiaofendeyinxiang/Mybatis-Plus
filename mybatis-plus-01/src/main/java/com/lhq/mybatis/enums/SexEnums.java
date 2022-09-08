package com.lhq.mybatis.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnums {
    MALE(0, "男"),FEMALE(1, "女");
    // 将注解所标识的值存储到数据库中
    @EnumValue
    private Integer sex;
    private String sexName;

    SexEnums(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
