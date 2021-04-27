package com.github.alone.module.sys.controller.login;

import com.github.alone.common.utils.ServletUtils;
import com.github.alone.module.sys.pojo.UserEntity;
import com.github.alone.module.sys.service.SysMenuService;
import com.github.alone.module.sys.util.TokenUtils;
import com.github.alone.module.sys.vo.out.LoginUser;
import com.github.alone.module.sys.vo.out.RouterOut;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.github.alone.common.core.domain.ResultInfo.success;

/**
 * @author XiaoY
 * 获取路由
 */
@RestController
@RequiredArgsConstructor
public class GetRoutersController {
    final TokenUtils tokenUtils;
    final SysMenuService sysMenuService;
    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public Map<String,Object> getRouters()
    {
        LoginUser loginUser = tokenUtils.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        UserEntity user = loginUser.getUser();
        List<RouterOut> menus = sysMenuService.selectMenuTreeByUserId(user.getUserId());
        return success(Map.of("menus",menus));
    }
}
