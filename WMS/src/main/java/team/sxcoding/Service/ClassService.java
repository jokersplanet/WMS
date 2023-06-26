package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Class;

import java.util.List;

public interface ClassService extends IService<Class> {

    /*判断class是否重复*/
    boolean isExistClassId(Integer id);

    boolean isExistClassName(String name,Integer groupId);


    Class  selectClassById(Integer id);

    /*查询类别*/
    List<Class> selectClassByGroupId(Integer groupId);

    List<Class> selectClass();
    ;

    /*删除类别*/
    boolean deleteClassById(Integer id);

    /*增加或者修改类别*/
    boolean saveOrUpdateClass(Class clazz);

    /*删除大类下所有小类*/
    boolean deleteClassByGroupId(Integer groupId);




}
