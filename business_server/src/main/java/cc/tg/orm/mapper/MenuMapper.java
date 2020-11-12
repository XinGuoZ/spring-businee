package cc.tg.orm.mapper;

import cc.tg.orm.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinguo
 * @since 2020-11-03
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByUserId(Long userId);

    List<Menu> getall();
}
