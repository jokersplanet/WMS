package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.PageResult;

import java.util.List;

public interface DepartmentService extends IService<Department> {


    /* 判断部门是否存在*/
    boolean isExistDepartment(Integer id);

    boolean isExistDepartmentName(String name);

    /*列出部门*/
    PageResult<Department> selectDepartment(Integer page,Integer count);

    List<Department> selectDepartmentIdAndName();

    /*创建部门*/
    boolean insertDepartment(Department department);

    /*修改部门*/
    boolean updateDepartment(Department department);

    /*删除部门*/
    boolean deleteDepartment(Integer id);
}
