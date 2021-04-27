package com.github.alone.module.sys.dao;


import com.github.alone.module.sys.pojo.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author XiaoY
 */
public interface ISysUserRoleDao extends JpaRepository<UserRoleEntity,Long> {
    List<UserRoleEntity> findByUserId(Long userId);

    @Query(value = "select role_id from sys_user_role where user_id = ?1",nativeQuery = true)
    List<Long> findRoleIdByUserId(@Param("userId") long userId);
}
