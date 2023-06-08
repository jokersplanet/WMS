package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Class;
import team.sxcoding.Entity.Department;

import java.util.List;

@Component
public interface DepartmentMapper  extends BaseMapper<Department> {
    /*
     * 判断部门是否存在*/
    @Select("SELECT COUNT(*) FROM department WHERE id = #{id}")
    Integer isExistDepartment(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM department WHERE name = #{name}")
    Integer isExistDepartmentName(@Param("name") String name);

    /*查询部门信息*/
    @Select("SELECT * FROM department")
    IPage<Department> selectDepartment(Page<Department> page);

    /*创建部门*/
    @Insert("INSERT INTO department (name,notes) VALUES (#{name},#{notes})")
    boolean createDepartment(Department department);

    /*列举部门信息*/
    @Select("SELECT * FROM department")
    List<Department> listDepartment();

    @Select("SELECT id,name FROM department")
    List<Department> selectDepartmentIdAndName();


    /*修改部门*/
    @Update("UPDATE department SET name = COALESCE(#{name},name), notes = COALESCE(#{notes},notes) WHERE id = #{id}")
    boolean updateDepartment(Department department);

    /*删除部门*/
    @Delete("DELETE FROM department WHERE id = #{id}")
    boolean deleteDepartmentById(@Param("id") Integer id);

}
