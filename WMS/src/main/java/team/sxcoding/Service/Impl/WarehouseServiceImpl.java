package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Department;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.Warehouse;
import team.sxcoding.Mapper.WarehouseMapper;
import team.sxcoding.Service.WarehouseService;

import java.util.List;

@Service("WarehouseService")
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {


    /* 判断仓库是否存在*/
    @Override
    public boolean isExistWarehouse(Integer id){
        if(baseMapper.isExistWarehouse(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistWarehouseName(String name){
        if(baseMapper.isExistWarehouseName(name)>0){
            return true;
        }
        return false;
    }

    @Override
    public  PageResult<Warehouse> selectWarehouse(Integer page, Integer count){
        Page<Warehouse> pageWarehouse = new Page<>(page,count);
        IPage<Warehouse> warehouseList = baseMapper.selectWarehouse(pageWarehouse);

        PageResult<Warehouse> pageResult = new PageResult<>();
        pageResult.setPages(warehouseList.getPages());
        pageResult.setData(warehouseList.getRecords());
        return pageResult;
    }

    @Override
    public List<Warehouse> selectWarehouseIdAndName(){
        return baseMapper.selectWarehouseIdAndName();
    }

    /*新建仓库*/
    @Override
    public boolean insertWarehouse(Warehouse warehouse){
        return baseMapper.insertWarehouse(warehouse);
    }

    /*修改仓库*/
    @Override
    public boolean updateWarehouse(Warehouse warehouse){
        return baseMapper.updateWarehouse(warehouse);
    }

    /*删除仓库*/
    @Override
    public boolean deleteWarehouse(Integer id){
        return  baseMapper.deleteWarehouseById(id);
    }

}
