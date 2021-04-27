package com.github.alone.module.sys.vo.in;

import com.github.alone.common.core.domain.PageBean;
import lombok.Data;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 13:25
 * @Description:
 **********************************/
@Data
public class SysUserBody extends PageBean {
    private Long userId;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private String password;
    private String status;
}
