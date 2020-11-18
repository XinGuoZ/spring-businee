package cc.tg.service.impl;

import cc.tg.config.exception.MyExceptionUtil;
import cc.tg.config.exception.SystemErrorType;
import cc.tg.orm.entity.Role;
import cc.tg.orm.mapper.RoleMapper;
import cc.tg.service.IRoleService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> getRoleBymenuId(Long menuId) {
        return baseMapper.getRoleBymenuId(menuId);
    }

    @Override
    public Role addOrEditRoles(Role role) {
        if (Objects.isNull(role.getId())){

            role.setCreateAt(DateUtil.now());
        }
        if (saveOrUpdate(role)) {
            return role;
        }
        return null;
    }

    @Override
    @Transactional
    public Object delRoles(Role role) {

        MyExceptionUtil.isFalse(updateById(role), SystemErrorType.DELETE_ERROR);
        return true;
    }
}
