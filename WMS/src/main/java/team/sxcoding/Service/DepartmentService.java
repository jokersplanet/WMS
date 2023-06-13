package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Department;

import java.util.List;

public interface DepartmentService extends IService<Department> {


    /* 判断部门是否存在*/
    boolean isExistDepartment(Integer id);

    boolean isExistDepartmentName(String name);

    /*列出部门*/
    IPage<Department> selectDepartment(Integer page, Integer count);

    List<Department> selectDepartmentIdAndName();

    /*创建或修改部门*/
    boolean saveOrUpdateDepartment(Department department);

    /*删除部门*/
    boolean deleteDepartment(Integer id);
}
