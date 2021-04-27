package com.github.alone.config;

import cn.hutool.core.util.IdUtil;
import com.github.alone.config.bean.SnowflakeBean;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author XiaoY
 * 自定义id生成策略
 */
@Component
public class JpaIdentifierGenerator extends IdentityGenerator {

    @Autowired
    private SnowflakeBean snowflakeBean;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return IdUtil.getSnowflake(snowflakeBean.getWorkerId(),snowflakeBean.getDataCenterId()).nextId();
    }

}
