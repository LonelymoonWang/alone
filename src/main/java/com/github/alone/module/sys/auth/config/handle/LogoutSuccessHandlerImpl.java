package com.github.alone.module.sys.auth.config.handle;


import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.github.alone.module.sys.auth.domain.LoginUser;
import com.github.alone.module.sys.auth.util.TokenUtils;
import com.github.alone.common.utils.ServletUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.alone.common.core.domain.ResultInfo.error;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/16 20:37
 * @Description: 自定义退出处理类，返回成功
 **********************************/
@Configuration
@RequiredArgsConstructor
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    final TokenUtils tokenUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        LoginUser loginUser = tokenUtils.getLoginUser(httpServletRequest);
        if (loginUser!=null){
            tokenUtils.delLoginUser(loginUser.getToken());
        }
        ServletUtils.renderString(httpServletResponse, JSON.toJSONString(error(HttpStatus.HTTP_OK, "退出成功")));
    }
}
