package com.github.alone.module.sys.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.alone.module.sys.role.dao.ISysRoleMapper;
import com.github.alone.module.sys.role.entity.RoleEntity;
import com.github.alone.module.sys.role.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 20:43
 * @Description:
 **********************************/
@Service(value = "sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<ISysRoleMapper, RoleEntity> implements ISysRoleService {
}
