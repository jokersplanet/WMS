package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Department;
import team.sxcoding.Mapper.DepartmentMapper;
import team.sxcoding.Service.DepartmentService;

import java.util.List;


@Service("DepartmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    /* 判断部门是否存在*/
    @Override
    public boolean isExistDepartment(Integer uid){
        if(count(new QueryWrapper<Department>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistDepartmentName(String name){
        if(count(new QueryWrapper<Department>().eq("name",name))>0){
            return true;
        }
        return false;
    }

    @Override
    public Department selectDepartmentByName(String name){
        return getOne(new QueryWrapper<Department>().eq("name",name));
    }

    @Override
    public Department selectDepartmentById(Integer uid){
        return getOne(new QueryWrapper<Department>().eq("uid",uid));
    }

    /*列出部门*/
    @Override
    public IPage<Department> selectDepartment(Integer page, Integer count){
        return page(new Page<>(page,count));
    }

    @Override
    public List<Department> selectDepartment(){
        return list();
    }

    /*创建或修改部门*/
    @Override
    public boolean saveOrUpdateDepartment(Department department){
        return saveOrUpdate(department);
    }


    /*删除部门*/
    @Override
    public boolean deleteDepartment(Integer uid){
        return remove(new QueryWrapper<Department>().eq("uid",uid));
    }


}
