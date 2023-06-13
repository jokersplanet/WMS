package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Group;
import team.sxcoding.Mapper.GroupMapper;
import team.sxcoding.Service.GroupService;

import java.util.List;

@Service("GroupService")
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    /*判断group是否存在*/
    @Override
    public boolean isExistGroupId(Integer id){
        if(baseMapper.isExistGroupId(id)>0){
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

    /*查询大类别*/
    @Override
    public List<Group> selectGroups(){
        return baseMapper.selectGroups();
    }

    @Override
    public List<Group> selectGroupIdAndName(){
        return baseMapper.selectGroupIdAndName();
    }

    /*增加大类别*/
    @Override
    public boolean insertGroup(Group group){
        return baseMapper.insertGroup(group);
    }

    /*删除类别*/
    @Override
    public boolean deleteGroupById(Integer id){
       return baseMapper.deleteGroupById(id);
    }

    /*修改类别*/
    @Override
    public boolean updateGroupById(Group group){
        return updateById(group);
    }
}
