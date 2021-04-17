package com.github.alone.config.exception;

import cn.hutool.http.HttpStatus;
import com.github.alone.common.exception.BaseException;
import com.github.alone.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;
import java.util.Objects;

import static com.github.alone.common.core.domain.ResultInfo.error;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 00:04
 * @Description: 全局异常处理器
 **********************************/
@RestControllerAdvice
public class GlobalExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public Map<String,Object> baseException(BaseException e)
    {
        return error(e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public Map<String,Object> businessException(CustomException e)
    {
        if ((e.getCode()==null))
        {
            return error(e.getMessage());
        }
        return error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Map<String,Object> handlerNoFoundException(Exception e)
    {
        log.error(e.getMessage(), e);
        return error(cn.hutool.http.HttpStatus.HTTP_NOT_FOUND, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Map<String,Object> handleAuthorizationException(AccessDeniedException e)
    {
        log.error(e.getMessage());
        return error(HttpStatus.HTTP_FORBIDDEN, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler(AccountExpiredException.class)
    public Map<String,Object> handleAccountExpiredException(AccountExpiredException e)
    {
        log.error(e.getMessage(), e);
        return error(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String,Object> handleUsernameNotFoundException(UsernameNotFoundException e)
    {
        log.error(e.getMessage(), e);
        return error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return error(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Map<String,Object> validatedBindException(BindException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return error(message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage(), e);
        String message = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return error(message);
    }

}
