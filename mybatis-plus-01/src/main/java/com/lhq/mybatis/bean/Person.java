package com.lhq.mybatis.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lhq.mybatis.enums.SexEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Person {
    private Integer id;
    private String name;
    @TableField("sex")
    private SexEnums sexEnums;
}
