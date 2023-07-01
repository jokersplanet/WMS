package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Warehouse;

import java.util.List;

public interface WarehouseService extends IService<Warehouse> {

    /* 判断仓库是否存在*/
    boolean isExistWarehouse(Integer uid);

    boolean isExistWarehouseName(String name);

    /*列出仓库*/
    IPage<Warehouse> selectWarehouse(Integer page, Integer count);

    List<Warehouse> selectWarehouse();

    Warehouse selectWarehouseById(Integer uid);



    /*创建或修改仓库*/
    boolean saveOrUpdateWarehouse(Warehouse warehouse);

    /*删除仓库*/
    boolean deleteWarehouse(Integer uid);

}
