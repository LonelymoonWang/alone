package com.github.alone.common.core.domain;

import lombok.Data;

import java.util.Map;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 13:08
 * @Description:
 **********************************/
@Data
public class PageBean {
    private int pageIndex = 1;
    private int pageSize = 10;
    private Map<String,Object> params;
}
