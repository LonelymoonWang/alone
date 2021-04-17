package com.github.alone.common.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 17:12
 * @Description: 基础实体类，与数据库相关字段对应，想加就继承此类
 **********************************/
@Data
public class BaseEntity implements Serializable {
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableField(value = "remark")
    private String remark;
    private Map<String, Object> params = new HashMap<>();
}
