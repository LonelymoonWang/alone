package com.github.alone.module.sys.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.mapping.PrimaryKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author XiaoY
 * 用户角色关联实体类
 */
@Data
@Entity
@Table(name = "sys_user_role")
@IdClass(UserRoleEntity.class)
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity implements Serializable {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Id
    @Column(name = "role_id")
    private Long roleId;

    public UserRoleEntity(Long roleId) {
        this.roleId = roleId;
    }
}
