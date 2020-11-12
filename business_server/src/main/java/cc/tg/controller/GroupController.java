package cc.tg.controller;


import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.SysGroup;
import cc.tg.service.IGroupService;
import cc.tg.service.impl.GroupServiceImpl;
import cc.tg.tools.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinguo
 * @since 2020-11-06
 */
@RestController
@RequestMapping("/tg/group")
@Api("管理员组")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @ApiOperation("获取管理员组")
    @GetMapping("loadGroupList")
    public ResultVO loadGroupList () {
        return ResultUtil.success(groupService.loadGroupList());
    }

    @ApiOperation("添加管理组")
    @PostMapping("addGroup")
    public ResultVO addGroup (@RequestBody SysGroup group) {
        return ResultUtil.success(groupService.addGroup(group));
    }

    @ApiOperation("添加管理组")
    @PostMapping("editGroup")
    public ResultVO editGroup (@RequestBody SysGroup group) {
        SysGroup sysGroup = groupService.editGroup(group);
        if (Objects.isNull(sysGroup)) {
            return ResultUtil.error("用户组更新失败");
        }else {
            return ResultUtil.success(sysGroup);
        }

    }

    @ApiOperation("删除管理组")
    @PostMapping("/delGroup/{id}")
    public ResultVO delGroup (@PathVariable("id") Integer id) {
        if (groupService.delGroup(id)) {
            return ResultUtil.success("删除成功", true);
        }else {
            return ResultUtil.error("删除失败");
        }

    }
}
