package cc.tg.service.impl;

import cc.tg.orm.entity.Role;
import cc.tg.orm.mapper.RoleMapper;
import cc.tg.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
