package com.github.alone.module.sys.service;

import cn.hutool.core.bean.BeanUtil;
import com.github.alone.common.core.domain.PageResInfo;
import com.github.alone.common.exception.BaseException;
import com.github.alone.module.sys.dao.ISysUserDao;
import com.github.alone.module.sys.pojo.UserEntity;
import com.github.alone.module.sys.vo.enums.UserStatus;
import com.github.alone.module.sys.vo.in.SysUserBody;
import com.github.alone.module.sys.vo.out.LoginUser;
import com.github.alone.module.sys.vo.out.UserOut;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.github.alone.common.core.domain.PageResBuild.buildPageResInfo;

/**
 * @author XiaoY
 * 用户服务层
 */
@Service(value = "sysUserService")
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysUserService {
    private static final Logger log = LoggerFactory.getLogger(SysUserService.class);
    final ISysUserDao userDao;

    public PageResInfo selectListByPage(SysUserBody body) {
        Page<UserEntity> all = userDao.findAll(PageRequest.of(body.getPageIndex(), body.getPageSize()));
        return buildPageResInfo(all);
    }

    public UserOut getInfoById(Long userId) {
        UserOut userOut = new UserOut();
        Optional<UserEntity> user = userDao.findById(userId);
        user.ifPresent(userEntity -> BeanUtil.copyProperties(userEntity, userOut));
        return userOut;
    }

    public void saveUserInfo(SysUserBody body) {
        UserEntity user = new UserEntity();
        BeanUtil.copyProperties(body, user);
        userDao.save(user);
    }

    public void updateUserById(SysUserBody body) {
        UserEntity user = new UserEntity();
        BeanUtil.copyProperties(body,user);
        userDao.save(user);
    }


    public void removeUserById(Long userId) {
        userDao.deleteById(userId);
    }

}
