package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Class;
import team.sxcoding.Entity.Group;

import java.util.List;


@Component
public interface GroupMapper extends BaseMapper<Group> {

    /*判断group是否存在*/
    @Select("SELECT COUNT(*) FROM group WHERE id = #{id}]")
    Integer isExistGroupId(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM group WHERE name = #{name}]")
    Integer isExistGroupName(@Param("name") String name);

    /*查询大类别*/
    @Select("SELECT * FROM group")
    List<Group> selectGroups();

    @Select("SELECT id,name FROM group")
    List<Group> selectGroupIdAndName();

    @Insert("INSERT INTO group (name) VALUES (#{name})")
    boolean insertGroup(Group group);

    /*删除类别*/
    @Delete("DELETE FROM group WHERE id = #{id}")
    boolean deleteGroupById(@Param("id") Integer id);

    /*修改类别*/
    @Update("UPDATE group SET name = COALESCE(#{name},name)) WHERE id = #{id}")
    boolean updateGroupById(Group group);
}
