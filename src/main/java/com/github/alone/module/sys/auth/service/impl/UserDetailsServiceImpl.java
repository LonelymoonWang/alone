package com.github.alone.module.sys.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.alone.common.exception.BaseException;
import com.github.alone.module.sys.auth.domain.LoginUser;
import com.github.alone.module.sys.auth.domain.UserStatus;
import com.github.alone.module.sys.user.entity.UserEntity;
import com.github.alone.module.sys.user.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/16 21:21
 * @Description: 用户验证处理
 **********************************/
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    final ISysUserService sysUserServiceApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserEntity> qw = new QueryWrapper<>();
        qw.eq("user_name",username);
        UserEntity user = sysUserServiceApi.getOne(qw);
        if (user == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if (UserStatus.DELETED.getCode().equals(user.getDeleted())) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        return new LoginUser(user,null);
    }
}
