package cc.tg.service;

import cc.tg.orm.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-03
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> getMenusByUserId();

    List<Menu> getMenuTree();

    Menu addorEditMenu(Menu menu);
}
