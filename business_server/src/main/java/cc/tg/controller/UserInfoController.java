package cc.tg.controller;


import cc.tg.model.dto.QueryDeptDTO;
import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.UserInfo;
import cc.tg.service.IUserInfoService;
import cc.tg.tools.MyConst;
import cc.tg.tools.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinguo
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/tg/userInfo")
@Api(value = "用户管理",tags = {"用户管理"})
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @ApiOperation("根据分组获取用户")
    @PostMapping("/getUsersByDeptId")
    public ResultVO getUsersByDeptId (@Valid @RequestBody QueryDeptDTO queryDeptDTO) {
        return ResultUtil.success(userInfoService.getUsersByDeptId(queryDeptDTO));
    }

    @ApiOperation("添加管理组")
    @PostMapping("addUser")
    public ResultVO addUser (@Valid @RequestBody UserInfo userInfo) {
        if(Objects.isNull(userInfoService.addUser(userInfo))) {
            return ResultUtil.error("管理员添加失败");
        }
        return ResultUtil.success(MyConst.OPT_SUCCESS,userInfo);
    }

    @ApiOperation("根据角色id获取用户")
    @PostMapping("getUserByRoleId/{roleId}")
    public ResultVO getUserByRoleId (@PathVariable("roleId") Long roleId) {

        return ResultUtil.success(userInfoService.getUserByRoleId(roleId));
    }


}
