package com.github.alone.common.core.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 17:12
 * @Description: 基础实体类，与数据库相关字段对应，想加就继承此类
 **********************************/
@Data
@Accessors(chain = true)
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Column(name = "create_by")
    private String createBy;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_by")
    private String updateBy;
    @Column(name = "update_time")
    private Date updateTime;
    @Column(name = "remark")
    private String remark;
}
