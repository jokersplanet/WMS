package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Class;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.Warehouse;

import java.util.List;


@Component
public interface WarehouseMapper extends BaseMapper<Warehouse> {

    /*判断仓库是否存在*/
    @Select("SELECT COUNT(*) FROM warehouse WHERE id = #{id}")
    Integer isExistWarehouse(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM warehouse WHERE name = #{name}")
    Integer isExistWarehouseName(@Param("name")String name);


    /*查询仓库*/
    @Select("SELECT * FROM warehouse")
    IPage<Warehouse> selectWarehouse(Page<Warehouse> page);

    /*创建仓库*/
    @Insert("INSERT INTO warehouse (name,head,value,address,notes) VALUES (#{name},#{head},#{value},#{address},#{notes})")
    boolean insertWarehouse(Warehouse warehouse);

    /*列举仓库信息*/
    @Select("SELECT * FROM warehouse")
    List<Warehouse> listWarehouse();

    @Select("SELECT id,name FROM warehouse")
    List<Warehouse> selectWarehouseIdAndName();

    /*修改仓库*/
    @Update("UPDATE warehouse SET name = COALESCE(#{name},name), head = COALESCE(#{head},head) , value = COALESCE(#{value},value), address = COALESCE(#{address},address), notes = COALESCE(#{notes},notes) WHERE id = #{id}")
    boolean updateWarehouse(Warehouse warehouse);

    /*删除仓库*/
    @Delete("DELETE FROM warehouse WHERE id = #{id}")
    boolean deleteWarehouseById(@Param("id") Integer id);
}
