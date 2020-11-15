package cc.tg.service;

import cc.tg.orm.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.security.RolesAllowed;
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
    /**
     * 获取权限列表
     * @Author     xinguoz
     * @Date        2020/11/15 23:28
     * @Param       menuId
     * @return      java.util.List<cc.tg.orm.entity.Role>
     **/
    List<Role> getRoleBymenuId(Long menuId);


    /**
     * 添加权限
     * @Author     xinguoz
     * @Date        2020/11/15 23:29
     * @Param       role
     * @return      cc.tg.orm.entity.Role
     **/
    Role addOrEditRoles(Role role);

    Object delRoles(Role role);

}
