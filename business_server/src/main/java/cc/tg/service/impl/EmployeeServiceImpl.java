package cc.tg.service.impl;

import cc.tg.model.dto.QueryEmpDTO;
import cc.tg.orm.entity.Employee;
import cc.tg.orm.mapper.EmployeeMapper;
import cc.tg.service.IEmployeeService;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xinguo
 * @since 2020-11-05
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Override
    public JSONObject getEmpssByDeptId(QueryEmpDTO queryEmpDTO) {
        JSONObject res = new JSONObject();
        Integer count = count(new QueryWrapper<Employee>().lambda()
                .eq(Employee::getDepartmentId, queryEmpDTO.getId()).eq(Employee::getRemoveStatus, "0"));
        res.set("totalSize",count);
        queryEmpDTO.cal();
        List<Employee> emps = baseMapper.getUsersByDeptId(queryEmpDTO);

        res.set("list", emps);
        return res;
    }
}
