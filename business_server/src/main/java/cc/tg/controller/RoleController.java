package cc.tg.controller;


import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.Role;
import cc.tg.service.IRoleService;
import cc.tg.tools.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return ResultUtil.success(roleService.list());
    }
}
