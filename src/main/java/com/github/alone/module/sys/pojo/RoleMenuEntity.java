package com.github.alone.module.sys.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.mapping.PrimaryKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author XiaoY
 * 角色权限关联实体类
 */
@Data
@Entity
@Table(name = "sys_role_menu")
@IdClass(RoleMenuEntity.class)
@EqualsAndHashCode()
public class RoleMenuEntity implements Serializable {
    @Id
    @Column(name = "role_id")
    private Long roleId;
    @Id
    @Column(name = "menu_id")
    private Long menuId;
}
