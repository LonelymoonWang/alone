package com.github.alone.test;

import com.github.alone.config.bean.SnowflakeBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SnowTests {

    @Autowired
    SnowflakeBean snowflakeBean;

    @Test
    public void test001(){
        Long workerId = snowflakeBean.getWorkerId();
        Long dataCenterId = snowflakeBean.getDataCenterId();
        System.out.println(workerId);
        System.out.println(dataCenterId);
    }
}
