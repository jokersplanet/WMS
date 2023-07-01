package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Warehouse;
import team.sxcoding.Mapper.WarehouseMapper;
import team.sxcoding.Service.WarehouseService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("WarehouseService")
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {


    /* 判断仓库是否存在*/
    @Override
    public boolean isExistWarehouse(Integer uid){
        if(count(new QueryWrapper<Warehouse>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistWarehouseName(String name){
        if(count(new QueryWrapper<Warehouse>().eq("name",name))>0){
            return true;
        }
        return false;
    }

    @Override
    public  IPage<Warehouse> selectWarehouse(Integer page, Integer count){
        return  page(new Page<>(page,count));
    }

    /*创建或修改仓库*/
    @Override
    public boolean saveOrUpdateWarehouse(Warehouse warehouse){
        return saveOrUpdate(warehouse);
    }


    /*删除仓库*/
    @Override
    public boolean deleteWarehouse(Integer uid){
        return  remove(new QueryWrapper<Warehouse>().eq("uid",uid));
    }

    @Override
    public List<Warehouse> selectWarehouse(){
        return list();
    }

    @Override
    public Warehouse selectWarehouseById(Integer uid){
        return  getOne(new QueryWrapper<Warehouse>().eq("uid",uid));
    }




}
