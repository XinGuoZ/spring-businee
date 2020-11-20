package cc.tg.service;

import cc.tg.model.dto.QueryEmpDTO;
import cc.tg.orm.entity.Employee;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-05
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 根据部门id查用户
     * @Author     xinguoz
     * @Date         12:19 上午
     * @Param       [queryEmpDTO]
     * @return      java.util.List<cc.tg.orm.entity.Department>
     **/
    JSONObject getEmpssByDeptId(QueryEmpDTO queryEmpDTO);

    /**
     * 添加修改管理员
     * @Author     xinguoz
     * @Date        2020/11/20 16:12
     * @Param       employee
     * @return      cc.tg.orm.entity.Employee
     **/
    Employee addOrEditEmp(Employee employee);

    /**
     *
     * 批量删除
     * @Author     xinguoz
     * @Date        2020/11/20 16:15
     * @Param       ids
     * @return      int
     **/
    int delEmp(List<Long> ids);
}
