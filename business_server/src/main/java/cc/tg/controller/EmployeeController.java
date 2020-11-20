package cc.tg.controller;


import cc.tg.model.dto.QueryEmpDTO;
import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.Employee;
import cc.tg.service.IEmployeeService;
import cc.tg.tools.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinguo
 * @since 2020-11-05
 */
@RestController
@RequestMapping("/tg/employee")
@Api(value = "人员管理",tags = {"人员管理"})
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation("根据部门获取用户")
    @PostMapping("/getUsersByDeptId")
    public ResultVO getEmpByDeptId (@Valid @RequestBody QueryEmpDTO queryEmpDTO) {
        return ResultUtil.success(employeeService.getEmpssByDeptId(queryEmpDTO));
    }

    @ApiOperation("添加和修改管理员")
    @PostMapping("/addOrEditEmp")
    public ResultVO addOrEditEmp(@Valid @RequestBody Employee employee) {

       return ResultUtil.success(employeeService.addOrEditEmp(employee));
    }

    @ApiOperation("根据id删除")
    @PostMapping("/delEmp")
    public ResultVO delEmp(@RequestParam("ids") List<Long> ids) {

        return ResultUtil.success(employeeService.delEmp(ids));
    }


}
