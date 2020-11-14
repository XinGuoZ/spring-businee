package cc.tg.orm.mapper;

import cc.tg.orm.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinguo
 * @since 2020-11-02
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getRoleBymenuId(@Param("menuId") Long menuId);
}
