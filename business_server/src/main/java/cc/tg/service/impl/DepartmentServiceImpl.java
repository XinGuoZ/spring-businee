package cc.tg.service.impl;

import cc.tg.orm.entity.Department;
import cc.tg.orm.mapper.DepartmentMapper;
import cc.tg.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Override
    public List<Department> loadDeptList() {
        return null;
    }

    @Override
    public Department addDept(Department dept) {
        return null;
    }

    @Override
    public Department editDept(Department dept) {
        return null;
    }

    @Override
    public boolean delDept(Integer id) {
        return false;
    }
}
