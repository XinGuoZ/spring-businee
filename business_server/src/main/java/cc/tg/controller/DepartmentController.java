package cc.tg.controller;


import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.Department;
import cc.tg.orm.entity.SysGroup;
import cc.tg.service.IDepartmentService;
import cc.tg.tools.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinguo
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/tg/department")
@Api(value = "部门管理",tags = {"部门管理"})
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @ApiOperation("获取部门信息")
    @GetMapping("loadDeptList")
    public ResultVO loadGroupList () {
        return ResultUtil.success(departmentService.loadDeptList());
    }

    @ApiOperation("添加部门")
    @PostMapping("addDept")
    public ResultVO addGroup (@RequestBody Department dept) {
        return ResultUtil.success(departmentService.addDept(dept));
    }

    @ApiOperation("修改部门组")
    @PostMapping("editDept")
    public ResultVO editGroup (@RequestBody Department dept) {
        Department department = departmentService.editDept(dept);
        if (Objects.isNull(department)) {
            return ResultUtil.error("用户组更新失败");
        }else {
            return ResultUtil.success(department);
        }

    }

    @ApiOperation("删除管理组")
    @PostMapping("/delDept/{id}")
    public ResultVO delDept (@PathVariable("id") Integer id) {
        if (departmentService.delDept(id)) {
            return ResultUtil.success("删除成功", true);
        }else {
            return ResultUtil.error("删除失败");
        }

    }
}
