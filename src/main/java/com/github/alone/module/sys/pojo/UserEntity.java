package com.github.alone.module.sys.pojo;

import com.github.alone.common.core.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.TABLE;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 00:32
 * @Description: 用户实体类
 **********************************/
@Data
@Entity
@Table(name = "sys_user")
@SQLDelete(sql = "update sys_user set deleted = 1 where userId = ?")
@Where(clause = "deleted = 0")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = TABLE,generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.github.alone.config.JpaIdentifierGenerator")
    private Long userId;
    @Column(name = "username")
    private String username;
    @Column(name = "nick_name")
    private String nickname;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "sex")
    private String sex;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    private String status;
    @Column(name = "login_ip")
    private String loginIp;
    @Column(name = "login_date")
    private Date loginDate;
    @Column(name = "deleted")
    private Integer deleted;
}
