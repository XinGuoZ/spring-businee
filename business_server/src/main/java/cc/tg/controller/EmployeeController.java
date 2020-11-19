package cc.tg.controller;


import cc.tg.model.dto.QueryEmpDTO;
import cc.tg.model.vo.ResultVO;
import cc.tg.service.IEmployeeService;
import cc.tg.tools.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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


}
