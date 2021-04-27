package com.github.alone.module.sys.dao;

import com.github.alone.module.sys.pojo.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author XiaoY
 * 角色持久层
 */
public interface ISysRoleDao extends JpaRepository<RoleEntity,Long> {
    /**
     * 根据角色IDs查询
     * @param roleIds 角色ids
     * @return List<RoleEntity>
     */
    List<RoleEntity> findByRoleIdIn(List<Long> roleIds);
}
