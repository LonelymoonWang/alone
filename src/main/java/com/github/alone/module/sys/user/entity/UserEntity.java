package com.github.alone.module.sys.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.alone.common.core.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 00:32
 * @Description: 用户实体类
 **********************************/
@Data
@TableName(value = "sys_user")
public class UserEntity extends BaseEntity {
    @TableId
    private Long userId;
    @TableField(value = "user_name")
    private String username;
    @TableField(value = "nick_name")
    private String nickname;
    @TableField(value = "email")
    private String email;
    @TableField(value = "phoneNumber")
    private String phoneNumber;
    @TableField(value = "sex")
    private String sex;
    @TableField(value = "avatar")
    private String avatar;
    @TableField(value = "password")
    private String password;
    @TableField(value = "status")
    private String status;
    @TableField(value = "login_ip")
    private String loginIp;
    @TableField(value = "login_date")
    private Date loginDate;
    @TableLogic
    @TableField(select = false)
    private String deleted;
}
