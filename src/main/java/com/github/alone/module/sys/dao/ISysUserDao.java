package com.github.alone.module.sys.dao;

import com.github.alone.module.sys.pojo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author XiaoY
 * 用户持久层
 */
public interface ISysUserDao extends JpaRepository<UserEntity,Long> {


    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return UserEntity
     */
    UserEntity findByUsername(String username);

}
