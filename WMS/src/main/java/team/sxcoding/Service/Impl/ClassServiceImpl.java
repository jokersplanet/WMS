package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Class;
import team.sxcoding.Mapper.ClassMapper;
import team.sxcoding.Service.ClassService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;




@Service("ClassService")
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    /*判断class是否重复*/
    @Override
    public  boolean isExistClassId(Integer id){
        if(count(new QueryWrapper<Class>().eq("id",id))>0){
            return true;
        }
        return false;
    }

    @Override
    public  boolean isExistClassName(String name,Integer groupId){
        if(count(new QueryWrapper<Class>().eq("name",name).eq("group_id",groupId))>0){
            return true;
        }
        return false;
    }


    @Override
    public Class  selectClassById(Integer uid){
        return getOne(new QueryWrapper<Class>().eq("uid",uid));
    }

    /*查询类别*/
    @Override
    public List<Class> selectClassByGroupId(Integer groupId){
        return list(new QueryWrapper<Class>().eq("group_id",groupId));
    }

    @Override
    public List<Class> selectClass(){
        return list();
    }


    /*删除类别*/
    @Override
    public boolean deleteClassById(Integer id){
        return removeById(id);
    }

    /*增加或者修改类别*/
    @Override
    public boolean saveOrUpdateClass(Class clazz){
        return saveOrUpdate(clazz);
    }

    /*删除大类下所有小类*/
    @Override
    public boolean deleteClassByGroupId(Integer groupId){
        return remove(new QueryWrapper<Class>().eq("group_id",groupId));
    }




}
