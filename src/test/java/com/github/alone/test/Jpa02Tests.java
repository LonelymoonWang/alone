package com.github.alone.test;

import com.github.alone.module.sys.dao.ISysMenuDao;
import com.github.alone.module.sys.dao.ISysRoleMenuDao;
import com.github.alone.module.sys.dao.ISysUserRoleDao;
import com.github.alone.module.sys.pojo.UserRoleEntity;
import com.github.alone.module.sys.service.SysMenuService;
import com.github.alone.module.sys.vo.out.RouterOut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Jpa02Tests {

    @Autowired
    private ISysUserRoleDao sysUserRoleDao;

    @Autowired
    private ISysMenuDao sysMenuDao;
    @Autowired
    private ISysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysMenuService sysMenuService;
    @Test
    public void test001(){
        List<UserRoleEntity> all =
                sysUserRoleDao.findAll();
        System.out.println(all);
    }

    @Test
    public void test002(){
        List<Long> roleIdByUserId = sysUserRoleDao.findRoleIdByUserId(1L);
        System.out.println(roleIdByUserId);
    }

    @Test
    public void test003(){
        List<RouterOut> routerOuts = sysMenuService.selectMenuTreeByUserId(1L);

        System.out.println(routerOuts);
    }

}
