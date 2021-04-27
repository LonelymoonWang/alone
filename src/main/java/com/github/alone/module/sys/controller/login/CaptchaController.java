package com.github.alone.module.sys.controller.login;

import cn.hutool.core.util.IdUtil;
import com.github.alone.common.constant.Constants;
import com.github.alone.common.utils.redis.RedisUtils;
import com.google.common.collect.Maps;
import com.wf.captcha.SpecCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.github.alone.common.core.domain.ResultInfo.success;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 01:30
 * @Description: 验证码操作处理
 **********************************/
@RestController
@RequiredArgsConstructor
public class CaptchaController {

    final RedisUtils redisUtils;

    /**生成验证码**/
    @GetMapping("/captchaImage")
    public Map<String,Object> getCaptcha(HttpServletRequest request, HttpServletResponse response)throws Exception{
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String uuid = IdUtil.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // 存入redis并设置过期时间为30分钟
        redisUtils.setCacheObject(verifyKey, verCode, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 将key和base64返回给前端
        Map<String, Object> result = Maps.newHashMap();
        result.put("uuid", uuid);
        result.put("img", specCaptcha.toBase64());
        return success(result);
    }

}
