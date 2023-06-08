package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Class;
import team.sxcoding.Mapper.ClassMapper;
import team.sxcoding.Service.ClassService;

import java.util.List;


@Service("ClassService")
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    /*判断class是否重复*/
    @Override
    public  boolean isExistClassId(Integer id){
        if(baseMapper.isExistClassId(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public  boolean isExistClassName(String name,Integer groupId){
        if (baseMapper.isExistClassName(name,groupId)>0) {
            return true;
        }
        return false;
    }

    /*查询类别*/
    @Override
    public List<Class> selectClassByGroupId(Integer groupId){
        return baseMapper.selectClassByGroupId(groupId);
    }

    @Override
    public List<Class> selectClassIdAndName(){
        return baseMapper.selectClassIdAndName();
    }

    /*增加类别*/
    @Override
    public boolean insertClassByGroupId(Class clazz){
        return baseMapper.insertClassByGroupId(clazz);
    }

    /*删除类别*/
    @Override
    public boolean deleteClassById(Integer id){
        return baseMapper.deleteClassById(id);
    }

    /*修改类别*/
    @Override
    public boolean updateClassById(Class clazz){
        return baseMapper.updateClassById(clazz);
    }

    /*删除大类下所有小类*/
    @Override
    public boolean deleteClassByGroupId(Integer groupId){
        return baseMapper.deleteClassByGroupId(groupId);
    }

}
