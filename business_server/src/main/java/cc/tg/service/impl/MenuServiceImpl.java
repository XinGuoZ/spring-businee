package cc.tg.service.impl;

import cc.tg.config.security.SecurityConfig;
import cc.tg.orm.entity.Menu;
import cc.tg.orm.entity.SysGroup;
import cc.tg.orm.entity.UserInfo;
import cc.tg.orm.mapper.MenuMapper;
import cc.tg.service.IMenuService;
import cc.tg.tools.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Menu addorEditMenu(Menu menu) {
        if (saveOrUpdate(menu)) {
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
        menu.setChildren(menus);
    }
}
