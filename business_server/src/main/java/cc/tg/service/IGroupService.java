package cc.tg.service;

import cc.tg.orm.entity.SysGroup;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-06
 */
public interface IGroupService extends IService<SysGroup> {

    /**
     * 获取管理员组
     * @Author     xinguoz
     * @Date        2020/11/6 21:27
     * @Param
     * @return      java.util.List<cc.tg.orm.entity.Group>
     **/
    SysGroup loadGroupList();

    /**
     * 添加管理员组
     * @Author     xinguoz
     * @Date        2020/11/6 22:44
     * @Param       group
     * @return      cc.tg.orm.entity.SysGroup
     **/
    SysGroup addGroup(SysGroup group);

    /**
     *修改部门名称
     * @Author     xinguoz
     * @Date        2020/11/6 23:38
     * @Param       group
     * @return      cc.tg.orm.entity.SysGroup
     **/
    SysGroup editGroup(SysGroup group);


    /**
     *
     * 删除成功
     * @Author     xinguoz
     * @Date        2020/11/6 23:43
     * @Param       id
     * @return      boolean
     **/
    boolean delGroup(Integer id);
}
