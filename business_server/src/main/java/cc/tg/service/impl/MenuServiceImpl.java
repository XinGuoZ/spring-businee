package cc.tg.service.impl;

import cc.tg.config.security.SecurityConfig;
import cc.tg.orm.entity.*;
import cc.tg.orm.mapper.MenuMapper;
import cc.tg.service.IMenuRoleService;
import cc.tg.service.IMenuService;
import cc.tg.service.IRoleService;
import cc.tg.tools.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Resource
    IRoleService roleService;
    @Resource
    IMenuRoleService menuRoleService;
    @Override
    public List<Menu> getMenusByUserId() {

        return baseMapper.getMenusByUserId(SecurityUtils.getCurrentHr().getId());
    }

    @Override
    public List<Menu> getMenuTree() {
        List<Menu> all = baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId,"0"));
        all.forEach(p->{
            recursionGroup(p);
        });
        return all;
    }

    @Override
    @Transactional
    public Menu addorEditMenu(Menu menu) {
        if (saveOrUpdate(menu)) {
            if (menu.getRoleIds().size()>0) {
                ArrayList<MenuRole> menuRoles = new ArrayList<>();
                menu.getRoleIds().forEach(p->{
                    MenuRole menuRole = new MenuRole();
                    menuRole.setMid(menu.getId()).setRid(p);
                    menuRoles.add(menuRole);
                });
                menuRoleService.remove(new QueryWrapper<MenuRole>().lambda().eq(MenuRole::getMid,menu.getId()).notIn(MenuRole::getRid,menu.getRoleIds()));
                menuRoleService.saveBatch(menuRoles);
            }
            return menu;
        }else {
            return null;
        }

    }

    /**
     * 递归
     * @Author     xinguoz
     * @Date        2020/11/6 21:46
     * @Param       group
     * @Param       childGroup
     * @return      void
     **/
    private void recursionGroup(Menu menu) {
        List<Menu> menus = baseMapper.selectList(new LambdaQueryWrapper<Menu>().eq(Menu::getParentId, menu.getId()));
        menus.forEach(p->{
            recursionGroup(p);
        });
        List<Role> roles = roleService.getRoleBymenuId(menu.getId());
        menu.setRoles(roles);
        menu.setChildren(menus);
    }
}
