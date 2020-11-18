package cc.tg.service.impl;

import cc.tg.orm.entity.SysGroup;
import cc.tg.orm.entity.UserInfo;
import cc.tg.orm.mapper.GroupMapper;
import cc.tg.service.IGroupService;
import cc.tg.tools.SecurityUtils;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-06
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, SysGroup> implements IGroupService {

    @Override
    public SysGroup loadGroupList() {
        //获取当前登入用户
        UserInfo userInfo = SecurityUtils.getCurrentHrUser();
        SysGroup group = baseMapper.selectById(userInfo.getGroupId());
        if (group.getIsParent()) {
            recursionGroup(group);
        }

        return group;
    }

    @Override
    public SysGroup addGroup(SysGroup group) {
        //找父节点信息
        SysGroup sysGroupF = getById(group.getParentId());
        group.setCreateAt(DateUtil.now());
        //更新部门为父节点
        if (!sysGroupF.getIsParent()) {
            sysGroupF.setIsParent(true);
            saveOrUpdate(sysGroupF);
        }
        saveOrUpdate(group);
        //处理当前部门节点
        group.setDepPath(sysGroupF.getDepPath()+"."+group.getId());
        saveOrUpdate(group);
        return group;
    }

    @Override
    public SysGroup editGroup(SysGroup group) {

        if (updateById(group)) {
            return group;
        }
        return null;
    }

    @Override
    public boolean delGroup(Integer id) {
        SysGroup sysGroup = getById(id);
        SysGroup parentNode = getById(sysGroup.getParentId());

        if(sysGroup.getIsParent()) {
            removeRecursionGroup(sysGroup);
        }
        int i = count(new LambdaQueryWrapper<SysGroup>().eq(SysGroup::getParentId, sysGroup.getIsParent()).eq(SysGroup::getRemoveStatus, "0"));
        sysGroup.setRemoveStatus("1");
        //将上一级节点改为字节点
        if (i==0) {
            parentNode.setIsParent(false);
            updateById(parentNode);
        }
        return updateById(sysGroup);
    }

    /**
     * 递归部门节点
     * @Author     xinguoz
     * @Date        2020/11/6 21:46
     * @Param       group
     * @Param       childGroup
     * @return      void
     **/
    private void recursionGroup(SysGroup group) {
        List<SysGroup> groups = baseMapper.selectList(new LambdaQueryWrapper<SysGroup>().eq(SysGroup::getParentId, group.getId()).eq(SysGroup::getRemoveStatus, "0"));
        groups.forEach(p->{
            if (p.getIsParent()) {
                recursionGroup(p);
            }

        });
        group.setChildGroup(groups);
    }
    /**
     * 递归删除下节点
     * @Author     xinguoz
     * @Date        2020/11/7 0:08
     * @Param       group
     * @return      void
     **/
    private void removeRecursionGroup(SysGroup group) {
        List<SysGroup> groups = baseMapper.selectList(new LambdaQueryWrapper<SysGroup>().eq(SysGroup::getParentId, group.getId()).eq(SysGroup::getRemoveStatus, "0"));
        for (SysGroup p : groups) {
            if (p.getIsParent()) {
                recursionGroup(p);
            }
            p.setRemoveStatus("1");
            saveOrUpdate(p);
        }
    }
}

