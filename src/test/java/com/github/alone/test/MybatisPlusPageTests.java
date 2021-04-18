package com.github.alone.test;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.alone.module.sys.user.entity.UserEntity;
import com.github.alone.module.sys.user.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**********************************
 * @Author: WSIR
 * @Date: 2021/4/18 12:33
 * @Description: 分页测试
 **********************************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusPageTests {

    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void test001(){
        IPage<UserEntity> page = new Page<>(1,1);
        IPage<UserEntity> page1 = sysUserService.page(page);
        String s = JSON.toJSONString(page1);
        System.out.println(s);
    }

    @Test
    public void test002(){
        UserEntity byId = sysUserService.getById(1L);
        System.out.println(byId);
    }

    @Test
    public void test003(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(2L);
        userEntity.setNickname("张三");
        sysUserService.updateById(userEntity);
        UserEntity byId = sysUserService.getById(2L);
        System.out.println(byId);
    }

}
