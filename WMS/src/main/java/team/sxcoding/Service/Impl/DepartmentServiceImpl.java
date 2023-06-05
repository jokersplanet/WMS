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

    /*创建部门*/
    @Override
    public List<Department> createDepartment(Department department){
        baseMapper.createDepartment(department);
         return baseMapper.listDepartment();
    }


    /*修改部门*/
    @Override
    public List<Department> updateDepartment(Department department){
       baseMapper.updateDepartment(department);
       return baseMapper.listDepartment();
    }

    /*删除部门*/
    @Override
    public List<Department> deleteDepartment(Integer id){
        baseMapper.deleteDepartmentById(id);
        return baseMapper.listDepartment();
    }


}
