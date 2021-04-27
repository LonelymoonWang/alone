package com.github.alone.module.sys.service;

import cn.hutool.core.bean.BeanUtil;
import com.github.alone.common.core.domain.PageResInfo;
import com.github.alone.module.sys.dao.ISysRoleDao;
import com.github.alone.module.sys.dao.ISysUserRoleDao;
import com.github.alone.module.sys.pojo.RoleEntity;
import com.github.alone.module.sys.pojo.UserEntity;
import com.github.alone.module.sys.vo.in.SysRoleBody;
import com.github.alone.module.sys.vo.out.RoleOut;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.alone.common.core.domain.PageResBuild.buildPageResInfo;
import static com.github.alone.service.SecurityService.isAdmin;

/**
 * @author XiaoY
 * 角色服务层
 */
@Service(value = "sysRoleService")
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysRoleService {

    final ISysRoleDao roleDao;
    final ISysUserRoleDao sysUserRoleDao;


    public PageResInfo selectListByPage(SysRoleBody body) {
        Page<RoleEntity> all = roleDao.findAll(PageRequest.of(body.getPageIndex(), body.getPageSize()));
        return buildPageResInfo(all);
    }

    public RoleOut getInfoById(Long roleId) {
        RoleOut roleOut = new RoleOut();
        Optional<RoleEntity> roleEntity = roleDao.findById(roleId);
        roleEntity.ifPresent(role -> BeanUtil.copyProperties(role,roleOut));
        return roleOut;
    }

    public void saveRole(SysRoleBody body) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtil.copyProperties(body,roleEntity);
        roleDao.save(roleEntity);
    }

    public void updateRole(SysRoleBody body) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtil.copyProperties(body,roleEntity);
        roleDao.save(roleEntity);
    }

    public void removeRoleById(Long roleId) {
        roleDao.deleteById(roleId);
    }

    public Set<String> getRolePermission(Long userId) {
        Set<String> roles = new HashSet<String>();
        if (isAdmin(userId)) {
            roles.add("admin");
        }else{
            List<Long> roleIdByUserId = sysUserRoleDao.findRoleIdByUserId(userId);
            List<RoleEntity> roleEntityList = roleDao.findByRoleIdIn(roleIdByUserId);
            Set<String> roleKeys =
                    roleEntityList.stream().map(RoleEntity::getRoleKey).collect(Collectors.toSet());
            roles.addAll(roleKeys);
        }
        return roles;
    }
}
