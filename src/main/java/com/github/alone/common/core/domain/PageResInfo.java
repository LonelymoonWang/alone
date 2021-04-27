package com.github.alone.common.core.domain;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author XiaoY
 */
@Data
public class PageResInfo {
    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows;


}
