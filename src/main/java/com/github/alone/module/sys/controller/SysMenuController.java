package com.github.alone.module.sys.controller;

import com.github.alone.common.utils.ServletUtils;
import com.github.alone.module.sys.service.SysMenuService;
import com.github.alone.module.sys.util.TokenUtils;
import com.github.alone.module.sys.vo.in.SysMenuBody;
import com.github.alone.module.sys.vo.out.LoginUser;
import com.github.alone.module.sys.vo.out.MenuOut;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.github.alone.common.core.domain.ResultInfo.success;

/**
 * @author XiaoY
 * 菜单控制器
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sys/menu")
public class SysMenuController {

    final TokenUtils tokenUtils;
    final SysMenuService sysMenuService;

    @GetMapping("/list")
    public Map<String,Object> list(SysMenuBody menuBody)
    {
        LoginUser loginUser = tokenUtils.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getUser().getUserId();
        List<MenuOut> menus = sysMenuService.selectMenuList(menuBody, userId);
        return success(Map.of("menus",menus));
    }


}
