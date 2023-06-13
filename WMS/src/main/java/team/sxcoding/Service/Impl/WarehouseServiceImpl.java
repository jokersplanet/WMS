package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Class;
import team.sxcoding.Entity.Unit;
import team.sxcoding.Entity.Warehouse;
import team.sxcoding.Mapper.WarehouseMapper;
import team.sxcoding.Service.WarehouseService;

import java.util.List;

@Service("WarehouseService")
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {


    /* 判断仓库是否存在*/
    @Override
    public boolean isExistWarehouse(Integer id){
        if(count(new QueryWrapper<Warehouse>().eq("id",id))>0){
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

    @Override
    public List<Warehouse> selectWarehouseIdAndName(){
        return baseMapper.selectList(new QueryWrapper<Warehouse>().select("id","name"));
    }

    /*创建或修改仓库*/
    @Override
    public boolean saveOrUpdateWarehouse(Warehouse warehouse){
        return saveOrUpdate(warehouse);
    }


    /*删除仓库*/
    @Override
    public boolean deleteWarehouse(Integer id){
        return  removeById(id);
    }

}
