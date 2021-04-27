package com.github.alone.module.sys.dao;

import com.github.alone.module.sys.pojo.MenuEntity;
import com.github.alone.module.sys.pojo.RoleMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author XiaoY
 */
public interface ISysRoleMenuDao extends JpaRepository<RoleMenuEntity,Long> {

    Optional<RoleMenuEntity> findByRoleIdIn(List<Long> roleIdByUserId);
}
