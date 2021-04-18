package com.github.alone.module.sys.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.alone.module.sys.role.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 20:41
 * @Description: 角色持久层mapper
 **********************************/
@Mapper
public interface ISysRoleMapper extends BaseMapper<RoleEntity> {
}
