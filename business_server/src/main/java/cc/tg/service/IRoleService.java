package cc.tg.service;

import cc.tg.orm.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-02
 */
public interface IRoleService extends IService<Role> {
    List<Role> getRoleBymenuId(Long menuId);

}
