package com.github.alone.module.sys.manager;

import com.github.alone.common.exception.BaseException;
import com.github.alone.module.sys.dao.ISysUserDao;
import com.github.alone.module.sys.pojo.UserEntity;
import com.github.alone.module.sys.service.SysMenuService;
import com.github.alone.module.sys.vo.enums.UserStatus;
import com.github.alone.module.sys.vo.out.LoginUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author XiaoY
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LoginManager implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(LoginManager.class);
    final ISysUserDao userDao;
    final SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(username);
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
        return createLoginUser(user);
    }

    public UserDetails createLoginUser(UserEntity user)
    {
        return new LoginUser(user, sysMenuService.getMenuPermission(user.getUserId()));
    }
}
