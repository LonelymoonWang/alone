package com.github.alone.module.sys.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.alone.common.core.entity.BaseEntity;
import lombok.Data;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 20:42
 * @Description:
 **********************************/
@Data
@TableName(value = "sys_role")
public class RoleEntity extends BaseEntity {
    @TableId(value = "role_id")
    private Long roleId;
    @TableField(value = "role_name")
    private String roleName;
    @TableField(value = "role_key")
    private String roleKey;
    @TableField(value = "status")
    private String status;
    @TableLogic
    @TableField(select = false)
    private String deleted;

}
