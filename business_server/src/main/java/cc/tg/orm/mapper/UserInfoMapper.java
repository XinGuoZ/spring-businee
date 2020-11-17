package cc.tg.orm.mapper;

import cc.tg.model.dto.QueryDeptDTO;
import cc.tg.orm.entity.Role;
import cc.tg.orm.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinguo
 * @since 2020-11-02
 */
public interface UserInfoMapper  extends BaseMapper<UserInfo>{


    UserInfo getByUsername(String username);

    List<Role> getRolesByUserId(Long id);

    List<UserInfo> getUserByRoleId(Long roleId);

    List<UserInfo> getUsersByDeptId(QueryDeptDTO queryDeptDTO);
}
