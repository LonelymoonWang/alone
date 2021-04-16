package com.github.alone.auth.persitence;

import lombok.Data;

import java.util.Date;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/17 00:32
 * @Description:
 **********************************/
@Data
public class UserEntity {
    private Long userId;
    private String username;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String password;
    private String salt;
    private String status;
    private String loginIp;
    private Date loginDate;
}
