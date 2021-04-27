package com.github.alone.test;

import com.alibaba.fastjson.JSON;
import com.github.alone.common.constant.UserConstants;
import com.github.alone.module.sys.dao.ISysMenuDao;
import com.github.alone.module.sys.dao.ISysUserDao;
import com.github.alone.module.sys.pojo.MenuEntity;
import com.github.alone.module.sys.pojo.UserEntity;
import com.github.alone.module.sys.vo.out.MetaOut;
import com.github.alone.module.sys.vo.out.RouterOut;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTests {

    @Autowired
    private ISysUserDao userDao;

    @Autowired
    private ISysMenuDao menuDao;


    @Test
    public void test001(){
        UserEntity admin = userDao.findByUsername("admin");
        System.out.println(admin);
    }

    @Test
    public void test002(){
        List<MenuEntity> all = menuDao.findAll();
        List<MenuEntity> menuEntities = newMenuTree(all);
        String s = JSON.toJSONString(menuEntities);
        System.out.println(s);
        String s1 = JSON.toJSONString(buildMenus(menuEntities));
        System.out.println(s1);
    }

    public List<MenuEntity> newMenuTree(List<MenuEntity> menuEntityList){
        List<MenuEntity> baseLists = new ArrayList<>();
        // 总菜单，出一级菜单，一级菜单没有父id
        for (MenuEntity e : menuEntityList) {
            if (e.getParentId() == 0L) {
                baseLists.add(e);
            }
        }
        // 遍历一级菜单
        for (MenuEntity e : baseLists) {
            // 将子元素 set进一级菜单里面   getChild()方法在下方
            e.setMenus(getChild(e.getMenuId(), menuEntityList));
        }

        return baseLists;
    }

    private List<MenuEntity> getChild(Long pid, List<MenuEntity> menuEntityList) {
        List<MenuEntity> childs = new ArrayList<>();
        for (MenuEntity e : menuEntityList) {
            if (e.getParentId().equals(pid)) {
                // 子菜单的下级菜单
                childs.add(e);
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (MenuEntity e : childs) {
            // 继续添加子元素
            e.setMenus(getChild(e.getMenuId(), menuEntityList));
        }
        //停下来的条件，如果 没有子元素了，则停下来
        if (childs.size() == 0) {
            return null;
        }
        return childs;
    }


    public List<RouterOut> buildMenus(List<MenuEntity> menus) {
        List<RouterOut> routers = new LinkedList<RouterOut>();
        for (MenuEntity menu : menus)
        {
            RouterOut router = new RouterOut();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaOut(menu.getMenuName(), menu.getIcon(), menu.getIsCache() == 1));
            List<MenuEntity> cMenus = menu.getMenus();
            if (CollectionUtils.isNotEmpty(cMenus) && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            else if (isMenuFrame(menu))
            {
                router.setMeta(null);
                List<RouterOut> childrenList = new ArrayList<RouterOut>();
                RouterOut children = new RouterOut();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaOut(menu.getMenuName(), menu.getIcon(),
                        menu.getIsCache() == 1));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }



    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(MenuEntity menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(MenuEntity menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame()))
        {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(MenuEntity menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu))
        {
            component = menu.getComponent();
        }
        else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu))
        {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(MenuEntity menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame()==UserConstants.NO_FRAME;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(MenuEntity menu) {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }



}
