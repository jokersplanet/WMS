package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.PageResult;

import java.util.List;

public interface DepartmentService extends IService<Department> {


    /* 判断部门是否存在*/
    boolean isExistDepartment(Integer id);

    /*列出部门*/
    PageResult<Department> selectDepartment(Integer page,Integer count);

    /*创建部门*/
    List<Department> createDepartment(Department department);

    /*修改部门*/
    List<Department> updateDepartment(Department department);

    /*删除部门*/
    List<Department> deleteDepartment(Integer id);
}
