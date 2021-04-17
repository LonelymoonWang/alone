package com.github.alone.module.sys.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.alone.module.sys.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 17:29
 * @Description:
 **********************************/
@Mapper
public interface ISysUserMapper extends BaseMapper<UserEntity> {
}
