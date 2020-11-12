package cc.tg.orm.mapper;

import cc.tg.orm.entity.SysGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinguo
 * @since 2020-11-06
 */
public interface GroupMapper extends BaseMapper<SysGroup> {

    @Update("UPDATE sys_group SET remove_status = '1' WHERE id in (#{ids})")
    int removeIds(@Param("ids") Integer[] ids);
}
