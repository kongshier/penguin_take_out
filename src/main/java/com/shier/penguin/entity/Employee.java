package com.shier.penguin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工实体、
 * @author Shier 2022/9/26
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;

    private String password;

    private String phone;

    private String sex;

    /**
     * 身份证号码 驼峰命名，与数据的下划线，没有影响，因为打开了驼峰命名映射map-underscore-to-camel-case: true
     */
    private String idNumber;

    private Integer status;

    /**
     * 插入时填充字段
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 插入和更新时填充字段
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 插入时填充字段
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 插入和更新时填充字段
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
