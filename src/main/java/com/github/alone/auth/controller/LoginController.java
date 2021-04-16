package com.github.alone.auth.controller;

import com.github.alone.auth.domain.LoginBody;
import com.github.alone.auth.domain.LoginUser;
import com.github.alone.auth.exception.CaptchaException;
import com.github.alone.auth.exception.CaptchaExpireException;
import com.github.alone.auth.exception.UserPasswordNotMatchException;
import com.github.alone.auth.util.TokenUtils;
import com.github.alone.common.constant.Constants;
import com.github.alone.common.exception.CustomException;
import com.github.alone.common.utils.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Maps;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.github.alone.common.core.domain.ResultInfo.success;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/16 21:25
 * @Description: 登录控制器
 **********************************/
@RestController
@RequiredArgsConstructor
public class LoginController {


    final RedisUtils redisUtils;
    final AuthenticationManager authenticationManager;
    final TokenUtils tokenUtils;


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginBody loginBody)
    {
        // 生成令牌
        String token = login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        return success(Maps.newHashMap(Constants.TOKEN,token));
    }


    public String login(String username, String password, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisUtils.getCacheObject(verifyKey);
        redisUtils.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                throw new UserPasswordNotMatchException();
            }
            else
            {
                throw new CustomException(e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenUtils.createToken(loginUser);
    }
}
