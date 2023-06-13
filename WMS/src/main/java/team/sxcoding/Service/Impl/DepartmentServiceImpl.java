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
    public boolean isExistDepartment(Integer id){
        if(count(new QueryWrapper<Department>().eq("id",id))>0){
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

    /*列出部门*/
    @Override
    public IPage<Department> selectDepartment(Integer page, Integer count){
        return page(new Page<>(page,count));
    }

    @Override
    public List<Department> selectDepartmentIdAndName(){
        return baseMapper.selectList(new QueryWrapper<Department>().select("id","name"));
    }

    /*创建或修改部门*/
    @Override
    public boolean saveOrUpdateDepartment(Department department){
        return saveOrUpdate(department);
    }


    /*删除部门*/
    @Override
    public boolean deleteDepartment(Integer id){
        return remove(new QueryWrapper<Department>().eq("id",id));
    }


}
