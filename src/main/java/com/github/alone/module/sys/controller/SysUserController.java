package com.github.alone.module.sys.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.alone.module.sys.pojo.UserEntity;
import com.github.alone.module.sys.service.SysUserService;
import com.github.alone.module.sys.vo.in.SysUserBody;
import com.github.alone.module.sys.vo.out.UserOut;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    final SysUserService sysUserService;

    /**分页查询总数**/
    @GetMapping(value = "/getListByPage")
    public Map<String, Object> getListByPage(SysUserBody body){
        return success(sysUserService.selectListByPage(body),null);
    }
    /**查询单个**/
    @GetMapping(value = {"/getInfo","/"})
    public Map<String, Object> getInfo(SysUserBody body){
        UserOut user = sysUserService.getInfoById(body.getUserId());
        return success(Map.of("user",user));
    }
    /**新增**/
    @RequestMapping(value = "/addInfo")
    public Map<String, Object> saveInfo(@RequestBody SysUserBody body){
        sysUserService.saveUserInfo(body);
        return success();
    }
    /**修改**/
    @RequestMapping(value = "/modifyInfo")
    public Map<String, Object> modifyInfo(@RequestBody SysUserBody body){
        sysUserService.updateUserById(body);
        return success();
    }
    /**删除**/
    @RequestMapping(value = "/delInfo")
    public Map<String, Object> delInfo(Long userId){
        sysUserService.removeUserById(userId);
        return success();
    }
}
