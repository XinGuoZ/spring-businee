package cc.tg.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

}
