package com.github.alone.module.sys.role.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.alone.module.sys.role.dao.ISysRoleMapper;
import com.github.alone.module.sys.role.entity.RoleEntity;
import com.github.alone.module.sys.role.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 20:40
 * @Description:
 **********************************/
@Service(value = "sysRoleServiceApi")
public class SysRoleServiceApiImpl extends ServiceImpl<ISysRoleMapper, RoleEntity> implements ISysRoleService {
}
