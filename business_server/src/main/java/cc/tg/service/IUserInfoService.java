package cc.tg.service;

import cc.tg.model.dto.QueryDeptDTO;
import cc.tg.orm.entity.UserInfo;
import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-02
 */
public interface IUserInfoService {

    /**
     * 根据部门id分页查询
     * @Author     xinguoz
     * @Date        2020/11/8 13:48
     * @Param       queryDeptDTO
     * @return      java.util.List<cc.tg.orm.entity.UserInfo>
     **/
    JSONObject getUsersByDeptId(QueryDeptDTO queryDeptDTO);

    /**
     * @Author     xinguoz
     * @Date        2020/11/8 13:48
     * @Param       userInfo
     * @return      cc.tg.orm.entity.UserInfo
     **/
    UserInfo addUser(UserInfo userInfo);

    /**
     * 跟权限id获取用户
     * @Author     xinguoz
     * @Date        2020/11/10 0:15
     * @Param       roleId
     * @return      java.util.List<cc.tg.orm.entity.UserInfo>
     **/
    List<UserInfo> getUserByRoleId(Long roleId);

    /**
     * 添加和修改
     * @Author     xinguoz
     * @Date        2020/11/16 17:50
     * @Param       userInfo
     * @return      cc.tg.orm.entity.UserInfo
     **/
    UserInfo addOrEditUser(UserInfo userInfo);

    /**
     * 删除用户
     * @Author     xinguoz
     * @Date        2020/11/17 21:32
     * @Param       userInfo
     * @return      java.lang.Object
     **/
    int delUser(UserInfo userInfo);
}
