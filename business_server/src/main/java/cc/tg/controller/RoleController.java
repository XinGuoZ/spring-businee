package cc.tg.controller;


import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.Role;
import cc.tg.service.IRoleService;
import cc.tg.tools.ResultUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinguo
 * @since 2020-11-02
 */
@RestController
@RequestMapping("/tg/role")
@Api(value = "权限控制",tags = {"权限控制"})
public class RoleController {

    @Autowired
    IRoleService roleService;

    @ApiModelProperty("全部权限列表")
    @GetMapping("/roleTree")
    public ResultVO getRoles() {
        List<Role> list = roleService.list(new LambdaQueryWrapper<Role>().eq(Role::getRemoveStatus, "0"));
        return ResultUtil.success(JSONUtil.parse(list));
    }

    @PreAuthorize("hasauthority('super_admin','admin')")
    @ApiModelProperty("添加和修改权限")
    @PostMapping("/addOrEditRoles")
    public ResultVO addOrEditRoles(@RequestBody @Valid Role role) {
        return ResultUtil.success(roleService.addOrEditRoles(role));
    }
    @PreAuthorize("hasauthority('super_admin','admin')")
    @ApiModelProperty("删除权限")
    @PostMapping("/delRoles/{id}")
    public ResultVO delRoles(@PathVariable("id") Long id) {
        Role role = new Role();
        role.setId(id).setRemoveStatus("1");
        return ResultUtil.success(roleService.delRoles(role));
    }
}
