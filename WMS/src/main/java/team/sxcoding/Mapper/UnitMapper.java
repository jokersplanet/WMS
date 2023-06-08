package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Class;
import team.sxcoding.Entity.Group;
import team.sxcoding.Entity.Unit;

import java.util.List;

@Component
public interface UnitMapper extends BaseMapper<Unit> {

    /*判断unit是否存在*/
    @Select("SELECT COUNT(*) FROM unit WHERE id = #{id}]")
    Integer isExistUnitId(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM unit WHERE name = #{name}]")
    Integer isExistUnitName(@Param("name") String name);

    /*查询单位*/
    @Select("SELECT * FROM unit")
    List<Unit> selectUnits();

    @Select("SELECT id,name FROM unit")
    List<Unit> selectUnitIdAndName();

    /*创建单位*/
    @Insert("INSERT INTO unit (name) VALUES (#{name})")
    boolean insertUnit(Unit unit);

    /*删除单位*/
    @Delete("DELETE FROM unit WHERE id = #{id}")
    boolean deleteUnitById(@Param("id") Integer id);

    /*修改单位*/
    @Update("UPDATE unit SET name = COALESCE(#{name},name)) WHERE id = #{id}")
    boolean updateUnitById(Unit unit);
}
