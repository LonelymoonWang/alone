package com.github.alone.config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author XiaoY
 * 配置雪花算法的终端ID和数据中心ID
 */
@Component
@ConfigurationProperties(prefix = "snowflake")
@Data
public class SnowflakeBean {
    private Long workerId;
    private Long dataCenterId;
}
