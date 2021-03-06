package cc.tg.service.impl;

import cc.tg.config.exception.MyExceptionUtil;
import cc.tg.config.exception.SystemErrorType;
import cc.tg.model.dto.QueryDeptDTO;
import cc.tg.orm.entity.UserInfo;
import cc.tg.orm.entity.UserRole;
import cc.tg.orm.mapper.UserInfoMapper;
import cc.tg.service.IUserInfoService;
import cc.tg.service.IUserRoleService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class UserInfoServiceImpl implements IUserInfoService, UserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Resource
    private IUserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.getByUsername(s);
        if (Objects.isNull(userInfo)) {
            MyExceptionUtil.throwMsj(SystemErrorType.NOT_EXIST_USERNAME);
        }
        return userInfo;
    }

    @Override
    public JSONObject getUsersByDeptId(QueryDeptDTO queryDeptDTO) {
        JSONObject res = new JSONObject();
        Integer count = userInfoMapper.selectCount(new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getGroupId, queryDeptDTO.getId()).eq(UserInfo::getRemoveStatus, "0"));
        res.set("totalSize",count);
        queryDeptDTO.cal();
        List<UserInfo> userInfos = userInfoMapper.getUsersByDeptId(queryDeptDTO);
//        if(queryDeptDTO.getPagination()) {
//            queryDeptDTO.cal();
//            List<UserInfo> userInfos = userInfoMapper.selectList(new QueryWrapper<UserInfo>().select("id", "name", "phone", "telephone", "address", "username", "userface", "create_at", "group_id").lambda()
//                    .eq(UserInfo::getGroupId, queryDeptDTO.getId()).eq(UserInfo::getRemoveStatus, "0").last("limit "+queryDeptDTO.getStartRow()+","+queryDeptDTO.getEndRow()));
//                    res.set("list", userInfos);
//        }else {
//            List<UserInfo> userInfos = userInfoMapper.selectList(new QueryWrapper<UserInfo>().select("id", "name", "phone", "telephone", "address", "username", "userface", "create_at", "group_id").lambda()
//                    .eq(UserInfo::getGroupId, queryDeptDTO.getId()).eq(UserInfo::getRemoveStatus, "0"));
//            res.set("list", userInfos);
//        }
        res.set("list", userInfos);
        return res;
    }

    @Override
    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setCreateAt(DateUtil.now());
        int insert = userInfoMapper.insert(userInfo);
        if(insert>0) {
            return userInfo;
        }
        return null;
    }

    @Override
    public List<UserInfo> getUserByRoleId(Long roleId) {
        List<UserInfo> userInfos = userInfoMapper.getUserByRoleId(roleId);
        return userInfos;
    }

    @Override
    @Transactional
    public UserInfo addOrEditUser(UserInfo userInfo) {

        int i = 0;
        if (Objects.isNull(userInfo.getId())) {
            i = userInfoMapper.insert(userInfo);
        }else {
            i = userInfoMapper.updateById(userInfo);
        }
        List<Long> roleIds = userInfo.getRoleIds();
        List<UserRole> userRoles = new ArrayList<>();
        if (i>0&&roleIds.size()>0) {
            for (int j =0 ,size = roleIds.size();j<size;j++) {
                UserRole userRole = new UserRole();
                userRole.setRid(roleIds.get(j));
                userRole.setUserId(userInfo.getId());
                userRoles.add(userRole);
            }
            userRoles.forEach(p->{
                int count = userRoleService.count(new LambdaQueryWrapper<UserRole>().eq(UserRole::getRid, p.getRid()).eq(UserRole::getUserId, p.getUserId()));
                if (count<1) {
                    userRoleService.save(p);
                }
            });

            userRoleService.remove(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId,userInfo.getId()).notIn(UserRole::getRid,roleIds));
        }
        return userInfo;
    }

    @Override
    public int delUser(UserInfo userInfo) {
        return userInfoMapper.updateById(userInfo);
    }
}
