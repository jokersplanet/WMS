package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Class;
import team.sxcoding.Entity.Group;

import java.util.List;

public interface ClassService extends IService<Class> {

    /*判断class是否重复*/
    boolean isExistClassId(Integer id);

    boolean isExistClassName(String name,Integer groupId);

    /*查询类别*/
    List<Class> selectClassByGroupId(Integer groupId);

    List<Class> selectClassIdAndName();

    /*增加类别*/
    boolean insertClassByGroupId(Class clazz);

    /*删除类别*/
    boolean deleteClassById(Integer id);

    /*修改类别*/
    boolean updateClassById(Class clazz);

    /*删除大类下所有小类*/
    boolean deleteClassByGroupId(Integer groupId);


}
