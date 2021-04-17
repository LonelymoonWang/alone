package com.github.alone.module.sys.user.api;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.alone.module.sys.user.dao.ISysUserMapper;
import com.github.alone.module.sys.user.entity.UserEntity;
import com.github.alone.module.sys.user.service.ISysUserService;
import org.springframework.stereotype.Service;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 17:42
 * @Description: 暴露服务
 **********************************/
@Service(value = "sysUserServiceApi")
public class SysUserServiceApiImpl extends ServiceImpl<ISysUserMapper, UserEntity> implements ISysUserService {
}
