package com.github.alone.module.sys.vo.in;

import com.github.alone.common.core.domain.PageBean;
import lombok.Data;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 20:42
 * @Description:
 **********************************/
@Data
public class SysRoleBody extends PageBean {
    private Long roleId;
    private String roleName;
    private String roleKey;
}
