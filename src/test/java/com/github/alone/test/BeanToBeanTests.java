package com.github.alone.test;

import cn.hutool.core.bean.BeanUtil;
import com.github.alone.module.sys.user.domain.SysUserBody;
import com.github.alone.module.sys.user.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 13:39
 * @Description:
 **********************************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanToBeanTests {

    @Test
    public void test001(){
        SysUserBody sysUserBody = new SysUserBody();
        sysUserBody.setUserId(5L);
        sysUserBody.setNickname("李四");
        sysUserBody.setSex("0");
        UserEntity user01 = new UserEntity();
        BeanUtil.copyProperties(sysUserBody,user01);
        System.out.println(user01);
    }
}
