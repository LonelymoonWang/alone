package com.github.alone.module.sys.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.alone.module.sys.user.domain.SysUserBody;
import com.github.alone.module.sys.user.entity.UserEntity;
import com.github.alone.module.sys.user.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.github.alone.common.core.domain.ResultInfo.success;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 17:29
 * @Description: 用户管理控制器
 **********************************/
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys/user")
public class SysUserController {

    final ISysUserService sysUserService;

    /**分页查询总数**/
    @GetMapping(value = "/getListByPage")
    public Map<String, Object> getListByPage(SysUserBody body){
        IPage<UserEntity> page = new Page<>(body.getPageIndex(), body.getPageSize());
        return success(sysUserService.page(page));
    }
    /**查询单个**/
    @GetMapping(value = "/getInfo")
    public Map<String, Object> getInfo(SysUserBody body){
        sysUserService.getById(body.getUserId());
        return success();
    }
    /**新增**/
    @RequestMapping(value = "/addInfo")
    public Map<String, Object> saveInfo(@RequestBody SysUserBody body){
        UserEntity user = new UserEntity();
        BeanUtil.copyProperties(body,user);
        sysUserService.save(user);
        return success();
    }
    /**修改**/
    @RequestMapping(value = "/modifyInfo")
    public Map<String, Object> modifyInfo(@RequestBody SysUserBody body){
        UserEntity user = new UserEntity();
        BeanUtil.copyProperties(body,user);
        sysUserService.updateById(user);
        return success();
    }
    /**删除**/
    @RequestMapping(value = "/delInfo")
    public Map<String, Object> delInfo(Long userId){
        sysUserService.removeById(userId);
        return success();
    }
}
