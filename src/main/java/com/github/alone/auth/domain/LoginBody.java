package com.github.alone.auth.domain;

import lombok.Data;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/16 21:27
 * @Description:
 **********************************/
@Data
public class LoginBody {
    private String username;
    private String password;
    private String code;
    private String uuid;
}
