package cc.tg.service.impl;

import cc.tg.orm.entity.Department;
import cc.tg.orm.mapper.DepartmentMapper;
import cc.tg.service.IDepartmentService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
        List<Department> departmentList = list(new LambdaQueryWrapper<Department>().eq(Department::getRemoveStatus,"0").eq(Department::getParentId,"0"));
        departmentList.forEach(p->{
            if (p.getIsParent()) {
                replyList(p);
            }

        });

        return departmentList;
    }

    private void replyList(Department department) {
        List<Department> departments = list(new LambdaQueryWrapper<Department>().eq(Department::getParentId,department.getId()).eq(Department::getRemoveStatus,"0"));
        departments.forEach(p->{
            if (department.getIsParent()) {
                replyList(p);
            }
        });
        department.setDepartments(departments);
    }

    @Override
    public Department addDept(Department dept) {
        //找父节点信息
        Department departmentF = getById(dept.getParentId());
        departmentF.setCreateAt(DateUtil.now());
        //更新部门为父节点
        if (!departmentF.getIsParent()) {
            departmentF.setIsParent(true);
            saveOrUpdate(departmentF);
        }
        saveOrUpdate(dept);
        //处理当前部门节点
        dept.setDepPath(departmentF.getDepPath()+"."+dept.getId());
        saveOrUpdate(dept);
        return dept;
    }

    @Override
    public Department editDept(Department dept) {
        if (updateById(dept)) {
            return dept;
        }
        return null;
    }

    @Override
    public boolean delDept(Integer id) {
        Department dept = getById(id);
        Department parentNode = getById(dept.getParentId());

        if(dept.getIsParent()) {
            removeRecursionDept(dept);
        }
        int i = count(new LambdaQueryWrapper<Department>().eq(Department::getParentId, dept.getId()).eq(Department::getRemoveStatus, "0"));
        dept.setRemoveStatus("1");
        //将上一级节点改为字节点
        if (i==0) {
            parentNode.setIsParent(false);
            updateById(parentNode);
        }
        return updateById(dept);
    }

    /**
     * 递归删除下节点
     * @Author     xinguoz
     * @Date        2020/11/7 0:08
     * @Param       group
     * @return      void
     **/
    private void removeRecursionDept(Department dept) {
        List<Department> depts = baseMapper.selectList(new LambdaQueryWrapper<Department>().eq(Department::getParentId, dept.getId()).eq(Department::getRemoveStatus, "0"));
        for (Department p : depts) {
            if (p.getIsParent()) {
                removeRecursionDept(p);
            }
            p.setRemoveStatus("1");
            saveOrUpdate(p);
        }
    }
}
