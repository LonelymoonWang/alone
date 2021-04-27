package com.github.alone.module.sys.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaoY
 * 菜单实体类
 */
@Data
@Entity
@Table(name = "sys_menu")
@SQLDelete(sql = "update sys_menu set deleted = 1 where menuId = ?")
@Where(clause = "deleted = 0")
public class MenuEntity {

    @Id
    @GeneratedValue(generator = "snowFlakeId")
    @GenericGenerator(name = "snowFlakeId", strategy = "com.github.alone.config.JpaIdentifierGenerator")
    private Long menuId;
    @Column(name = "menu_name")
    private String menuName;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "order_num")
    private Integer orderNum;
    @Column(name = "path")
    private String path;
    @Column(name = "component")
    private String component;
    @Column(name = "is_frame")
    private int isFrame;
    @Column(name = "is_cache")
    private int isCache;
    @Column(name = "menu_type")
    private String menuType;
    @Column(name = "visible")
    private String visible;
    @Column(name = "status")
    private String status;
    @Column(name = "perms")
    private String perms;
    @Column(name = "icon")
    private String icon;
    @Column(name = "deleted")
    private Integer deleted;
    @Transient
    private List<MenuEntity> menus = new ArrayList<>();

}
