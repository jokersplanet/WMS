package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Class;

import java.util.List;

@Component
public interface ClassMapper extends BaseMapper<Class> {

    /*判断class是否重复*/
    @Select("SELECT COUNT(*) FROM class WHERE id = #{id}")
    Integer isExistClassId(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM class WHERE name = #{name} AND group_id = #{groupId}")
    Integer isExistClassName(@Param("name") String name,@Param("groupId")Integer groupId);

    @Select("SELECT id,name FROM class")
    List<Class> selectClassIdAndName();

    /*查询小类别*/
    @Select("SELECT * FROM class WHERE group_id = #{groupId}")
    List<Class> selectClassByGroupId(@Param("groupId") Integer groupId);

    /*新建小类别*/
    @Insert("INSERT INTO class (name,group_id) VALUES (#{name},#{groupId})")
    boolean insertClassByGroupId(Class clazz);

    @Delete("DELETE FROM class WHERE id = #{id}")
    boolean deleteClassById(@Param("id")Integer id);

    @Update("UPDATE class SET name = COALESCE(#{name},name), group_id = COALESCE(#{groupId},group_id) WHERE id = #{id}")
    boolean updateClassById(Class clazz);

    @Delete("DELETE FROM class WHERE group_id = #{groupId}")
    boolean deleteClassByGroupId(@Param("groupId") Integer groupId);


}
