package com.github.alone.module.sys.pojo;

import com.github.alone.common.core.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;


/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 20:42
 * @Description:
 **********************************/
@Data
@Entity
@Table(name = "sys_role")
@SQLDelete(sql = "update sys_role set deleted = 1 where roleId = ?")
@Where(clause = "deleted = 0")
public class RoleEntity extends BaseEntity {
    @Id
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.github.alone.config.JpaIdentifierGenerator")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_key")
    private String roleKey;
    @Column(name = "status")
    private String status;
    @Column(name = "deleted")
    private Integer deleted;


}
