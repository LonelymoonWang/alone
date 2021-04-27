package com.github.alone.module.sys.service;

import cn.hutool.core.bean.BeanUtil;
import com.github.alone.common.constant.UserConstants;
import com.github.alone.module.sys.dao.ISysMenuDao;
import com.github.alone.module.sys.dao.ISysRoleMenuDao;
import com.github.alone.module.sys.dao.ISysUserRoleDao;
import com.github.alone.module.sys.pojo.MenuEntity;
import com.github.alone.module.sys.pojo.RoleMenuEntity;
import com.github.alone.module.sys.vo.in.SysMenuBody;
import com.github.alone.module.sys.vo.out.MenuOut;
import com.github.alone.module.sys.vo.out.MetaOut;
import com.github.alone.module.sys.vo.out.RouterOut;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.alone.service.SecurityService.isAdmin;

/**
 * @author XiaoY
 */
@Service(value = "sysMenuService")
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SysMenuService {

    final ISysRoleMenuDao sysRoleMenuDao;
    final ISysUserRoleDao sysUserRoleDao;
    final ISysMenuDao sysMenuDao;

    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<>();
        // 管理员拥有所有权限
        if (isAdmin(userId)) {
            perms.add("*:*:*");
        }else{
            List<Long> roleIdByUserId = sysUserRoleDao.findRoleIdByUserId(userId);
            Optional<RoleMenuEntity> roleMenuEntityList =
                    sysRoleMenuDao.findByRoleIdIn(roleIdByUserId);
            if (roleMenuEntityList.isPresent()) {
                List<Long> menuIds =
                        roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
                List<MenuEntity> menus = sysMenuDao.findByMenuIdIn(menuIds);
                perms.addAll(menus.stream().map(MenuEntity::getPerms).collect(Collectors.toSet()));
            }
        }
        return perms;
    }

    public List<RouterOut> selectMenuTreeByUserId(Long userId) {
        List<MenuEntity> all = new ArrayList<>();
        if (isAdmin(userId)) {
            all = sysMenuDao.findAll();
        }else{
            // 根据用户id查询角色
            List<Long> roleIdByUserId = sysUserRoleDao.findRoleIdByUserId(userId);
            Optional<RoleMenuEntity> roleMenuEntityList =
                    sysRoleMenuDao.findByRoleIdIn(roleIdByUserId);
            if (roleMenuEntityList.isPresent()) {
                List<Long> menuIds =
                        roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
                all = sysMenuDao.findByMenuIdIn(menuIds);
            }
        }
        return buildMenus(newMenuTree(all));
    }

    public List<MenuOut> selectMenuList(SysMenuBody menuBody, Long userId) {
        List<MenuEntity> all = new ArrayList<>();
        if (isAdmin(userId)) {
            all = sysMenuDao.findAll();
        }else{
            List<Long> roleIdByUserId = sysUserRoleDao.findRoleIdByUserId(userId);
            Optional<RoleMenuEntity> roleMenuEntityList =
                    sysRoleMenuDao.findByRoleIdIn(roleIdByUserId);
            if (roleMenuEntityList.isPresent()) {
                List<Long> menuIds =
                        roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList());
                all = sysMenuDao.findByMenuIdIn(menuIds);
            }
        }

        return all.stream().map(menuEntity -> {
            MenuOut menuOut = new MenuOut();
            BeanUtil.copyProperties(menuEntity,menuOut);
            return menuOut;
        }).collect(Collectors.toList());

    }



    private List<RouterOut> buildMenus(List<MenuEntity> menus) {
        List<RouterOut> routers = new LinkedList<>();
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
                List<RouterOut> childrenList = new ArrayList<>();
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

    private List<MenuEntity> newMenuTree(List<MenuEntity> menuEntityList){
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

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    private String getRouteName(MenuEntity menu) {
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
    private String getRouterPath(MenuEntity menu) {
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
    private String getComponent(MenuEntity menu) {
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
    private boolean isMenuFrame(MenuEntity menu) {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame()==UserConstants.NO_FRAME;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isParentView(MenuEntity menu) {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }


}
