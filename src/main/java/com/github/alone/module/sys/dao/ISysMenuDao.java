package com.github.alone.module.sys.dao;

import com.github.alone.module.sys.pojo.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author XiaoY
 */
public interface ISysMenuDao extends JpaRepository<MenuEntity,Long> {
    /**
     * 根据菜单ids查询菜单
     * @param menuIds 菜单ids
     * @return list
     */
    List<MenuEntity> findByMenuIdIn(List<Long> menuIds);
}
