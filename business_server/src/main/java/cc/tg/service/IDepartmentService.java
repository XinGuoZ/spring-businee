package cc.tg.service;

import cc.tg.orm.entity.Department;
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
public interface IDepartmentService extends IService<Department> {

    List<Department> loadDeptList();

    Department addDept(Department dept);

    Department editDept(Department dept);

    boolean delDept(Integer id);
}
