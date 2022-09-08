package com.lhq.mybatis.bean;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version //标识版本号字段
    private Integer version;
}
