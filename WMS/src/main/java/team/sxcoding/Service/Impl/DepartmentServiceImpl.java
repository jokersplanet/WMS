package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Mapper.DepartmentMapper;
import team.sxcoding.Service.DepartmentService;

import java.util.List;


@Service("DepartmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    /* 判断部门是否存在*/
    @Override
    public boolean isExistDepartment(Integer id){
        if(baseMapper.isExistDepartment(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistDepartmentName(String name){
        if(baseMapper.isExistDepartmentName(name)>0){
            return true;
        }
        return false;
    }

    /*列出部门*/
    @Override
    public PageResult<Department> selectDepartment(Integer page, Integer count){
        Page<Department> pageDepartment = new Page<>(page,count);
        IPage<Department> departmentList = baseMapper.selectDepartment(pageDepartment);

        PageResult<Department> pageResult = new PageResult<>();
        pageResult.setPages(departmentList.getPages());
        pageResult.setData(departmentList.getRecords());
        return pageResult;
    }

    @Override
    public List<Department> selectDepartmentIdAndName(){
        return baseMapper.selectDepartmentIdAndName();
    }

    /*创建部门*/
    @Override
    public boolean insertDepartment(Department department){
        return baseMapper.createDepartment(department);
    }


    /*修改部门*/
    @Override
    public boolean updateDepartment(Department department){
          return baseMapper.updateDepartment(department);
    }

    /*删除部门*/
    @Override
    public boolean deleteDepartment(Integer id){
        return baseMapper.deleteDepartmentById(id);
    }


}
