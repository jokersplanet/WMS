package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Group;
import team.sxcoding.Mapper.GroupMapper;
import team.sxcoding.Service.GroupService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("GroupService")
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    /*判断group是否存在*/
    @Override
    public boolean isExistGroupId(Integer uid){
        if(baseMapper.isExistGroupId(uid)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistGroupName(String name){
        if(baseMapper.isExistGroupName(name)>0){
            return true;
        }
        return false;
    }

    @Override
    public Group  selectGroupById(Integer uid){
        return baseMapper.selectGroupById(uid);
    }

    @Override
    public Group selectGroupByName(String name){
        return baseMapper.selectGroupByName(name);
    }

    /*查询大类别*/
    @Override
    public List<Group> selectGroups(){
        return baseMapper.selectGroups();
    }



    /*增加大类别*/
    @Override
    public boolean insertGroup(Group group){
        return baseMapper.insertGroup(group);
    }

    /*删除类别*/
    @Override
    public boolean deleteGroupById(Integer uid){
       return baseMapper.deleteGroupById(uid);
    }

    /*修改类别*/
    @Override
    public boolean updateGroupById(Group group){
        return baseMapper.updateGroupById(group);
    }


}
