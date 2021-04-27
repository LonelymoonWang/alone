package com.github.alone.module.sys.controller.login;

import com.github.alone.common.utils.ServletUtils;
import com.github.alone.module.sys.dao.ISysRoleDao;
import com.github.alone.module.sys.pojo.UserEntity;
import com.github.alone.module.sys.service.SysMenuService;
import com.github.alone.module.sys.service.SysRoleService;
import com.github.alone.module.sys.util.TokenUtils;
import com.github.alone.module.sys.vo.out.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

import static com.github.alone.common.core.domain.ResultInfo.success;

/**
 * @author XiaoY
 */
@RestController
@RequiredArgsConstructor
public class GetInfoController {
    final TokenUtils tokenUtils;
    final SysRoleService sysRoleService;
    final SysMenuService sysMenuService;
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public Map<String, Object> getInfo()
    {
        LoginUser loginUser = tokenUtils.getLoginUser(ServletUtils.getRequest());
        UserEntity user = loginUser.getUser();
        // 角色集合
        Set<String> roles = sysRoleService.getRolePermission(user.getUserId());
        // 权限集合
        Set<String> permissions = sysMenuService.getMenuPermission(user.getUserId());
        return success(Map.of("user", user,"roles", roles,"permissions", permissions));
    }



}
