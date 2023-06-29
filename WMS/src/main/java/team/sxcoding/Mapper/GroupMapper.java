package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Group;

import java.util.List;


@Component
public interface GroupMapper extends BaseMapper<Group> {

    /*判断group是否存在*/
    @Select("SELECT COUNT(*) FROM  `group` WHERE uid = #{uid}")
    Integer isExistGroupId(@Param("uid") Integer uid);

    @Select("SELECT COUNT(*) FROM  `group` WHERE name = #{name}")
    Integer isExistGroupName(@Param("name") String name);

    @Select("SELECT * FROM  `group` WHERE uid = #{uid}")
    Group  selectGroupById(Integer uid);

    @Select("SELECT * FROM  `group` WHERE name = #{name}")
    Group selectGroupByName(String name);

    /*查询大类别*/
    @Select("SELECT * FROM  `group`")
    List<Group> selectGroups();



    @Insert("INSERT INTO  `group` (name) VALUES (#{name})")
    boolean insertGroup(Group group);

    /*删除类别*/
    @Delete("DELETE FROM  `group` WHERE uid = #{uid}")
    boolean deleteGroupById(@Param("uid") Integer uid);

    /*修改类别*/
    @Update("UPDATE  `group` SET name = COALESCE(#{name},name) WHERE uid = #{uid}")
    boolean updateGroupById(Group group);
}
