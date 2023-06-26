package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Group;

import java.util.List;

public interface GroupService extends IService<Group> {

    /*判断group是否存在*/
    boolean isExistGroupId(Integer id);

    boolean isExistGroupName(String name);

    Group  selectGroupById(Integer id);

    Group selectGroupByName(String name);

    /*查询大类别*/
    List<Group> selectGroups();


    /*增加大类别*/
    boolean insertGroup(Group group);

    /*删除类别*/
    boolean deleteGroupById(Integer id);

    /*修改类别*/
    boolean updateGroupById(Group group);


}
