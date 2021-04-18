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
    private Long pageIndex = 1L;
    private Long pageSize = 10L;
    private Map<String,Object> params;
}
