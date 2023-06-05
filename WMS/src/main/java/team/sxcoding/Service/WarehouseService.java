package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.Warehouse;

import java.util.List;

public interface WarehouseService extends IService<Warehouse> {

    /* 判断仓库是否存在*/
    boolean isExistWarehouse(Integer id);

    /*列出仓库*/
    PageResult<Warehouse> selectWarehouse(Integer page, Integer count);

    /*新建仓库*/
    List<Warehouse> createWarehouse(Warehouse warehouse);

    /*修改仓库*/
    List<Warehouse> updateWarehouse(Warehouse warehouse);

    /*删除仓库*/
    List<Warehouse> deleteWarehouse(Integer id);

}
