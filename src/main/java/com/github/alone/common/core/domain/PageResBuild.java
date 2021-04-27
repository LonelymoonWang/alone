package com.github.alone.common.core.domain;

import org.springframework.data.domain.Page;

public class PageResBuild {

    public static PageResInfo buildPageResInfo(Page<?> some){
        PageResInfo pageResInfo = new PageResInfo();
        pageResInfo.setRows(some.getContent());
        pageResInfo.setTotal(some.getTotalElements());
        return pageResInfo;
    }
}
