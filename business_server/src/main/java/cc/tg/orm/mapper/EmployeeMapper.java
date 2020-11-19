package cc.tg.orm.mapper;

import cc.tg.model.dto.QueryEmpDTO;
import cc.tg.orm.entity.Department;
import cc.tg.orm.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xinguo
 * @since 2020-11-05
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<Employee> getUsersByDeptId(QueryEmpDTO queryEmpDTO);
}
