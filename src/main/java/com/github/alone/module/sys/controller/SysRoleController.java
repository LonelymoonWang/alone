package com.github.alone.module.sys.controller;

import com.github.alone.module.sys.service.SysRoleService;
import com.github.alone.module.sys.vo.in.SysRoleBody;
import com.github.alone.module.sys.vo.out.RoleOut;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.github.alone.common.core.domain.ResultInfo.success;

/**
 * @author XiaoY
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys/role")
public class SysRoleController {

    final SysRoleService sysRoleService;

    /**分页查询总数**/
    @GetMapping(value = "/getListByPage")
    public Map<String, Object> getListByPage(SysRoleBody body){
        return success(sysRoleService.selectListByPage(body),null);
    }
    /**查询单个**/
    @GetMapping(value = "/getInfo")
    public Map<String, Object> getInfo(SysRoleBody body){
        RoleOut roleOut = sysRoleService.getInfoById(body.getRoleId());
        return success(Map.of("role",roleOut));
    }
    /**新增**/
    @RequestMapping(value = "/addInfo")
    public Map<String, Object> saveInfo(@RequestBody SysRoleBody body){
        sysRoleService.saveRole(body);
        return success();
    }
    /**修改**/
    @RequestMapping(value = "/modifyInfo")
    public Map<String, Object> modifyInfo(@RequestBody SysRoleBody body){
        sysRoleService.updateRole(body);
        return success();
    }
    /**删除**/
    @RequestMapping(value = "/delInfo")
    public Map<String, Object> delInfo(Long roleId){
        sysRoleService.removeRoleById(roleId);
        return success();
    }
}
