package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.Warehouse;

import java.util.List;

public interface WarehouseService extends IService<Warehouse> {

    /* 判断仓库是否存在*/
    boolean isExistWarehouse(Integer id);

    boolean isExistWarehouseName(String name);

    /*列出仓库*/
    PageResult<Warehouse> selectWarehouse(Integer page, Integer count);

    List<Warehouse> selectWarehouseIdAndName();

    /*新建仓库*/
    boolean insertWarehouse(Warehouse warehouse);

    /*修改仓库*/
    boolean updateWarehouse(Warehouse warehouse);

    /*删除仓库*/
    boolean deleteWarehouse(Integer id);

}
