package com.github.alone.module.sys.role.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.alone.module.sys.role.domain.SysRoleBody;
import com.github.alone.module.sys.role.entity.RoleEntity;
import com.github.alone.module.sys.role.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.github.alone.common.core.domain.ResultInfo.success;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 20:41
 * @Description:
 **********************************/
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys/role")
public class SysRoleController {

    final ISysRoleService sysRoleService;

    /**分页查询总数**/
    @GetMapping(value = "/getListByPage")
    public Map<String, Object> getListByPage(SysRoleBody body){
        IPage<RoleEntity> page = new Page<>(body.getPageIndex(), body.getPageSize());
        return success(sysRoleService.page(page));
    }
    /**查询单个**/
    @GetMapping(value = "/getInfo")
    public Map<String, Object> getInfo(SysRoleBody body){
        sysRoleService.getById(body.getRoleId());
        return success();
    }
    /**新增**/
    @RequestMapping(value = "/addInfo")
    public Map<String, Object> saveInfo(@RequestBody SysRoleBody body){
        RoleEntity roleEntity = new RoleEntity();
        BeanUtil.copyProperties(body,roleEntity);
        sysRoleService.save(roleEntity);
        return success();
    }
    /**修改**/
    @RequestMapping(value = "/modifyInfo")
    public Map<String, Object> modifyInfo(@RequestBody SysRoleBody body){
        RoleEntity roleEntity = new RoleEntity();
        BeanUtil.copyProperties(body,roleEntity);
        sysRoleService.updateById(roleEntity);
        return success();
    }
    /**删除**/
    @RequestMapping(value = "/delInfo")
    public Map<String, Object> delInfo(Long roleId){
        sysRoleService.removeById(roleId);
        return success();
    }
}
