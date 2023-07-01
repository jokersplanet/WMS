package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Unit;
import team.sxcoding.Mapper.UnitMapper;
import team.sxcoding.Service.UnitService;

import java.util.List;

@Service("UnitService")
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements UnitService {


    /*判断Unit是否存在*/
    @Override
    public boolean isExistUnitId(Integer uid){
        if(count(new QueryWrapper<Unit>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistUnitName(String name){
        if(count(new QueryWrapper<Unit>().eq("name",name))>0){
            return true;
        }
        return false;
    }

    @Override
    public Unit selectUnitById(Integer uid){
        return getOne(new QueryWrapper<Unit>().eq("uid",uid));
    }

    @Override
    public Unit selectUnitByName(String name){
        return getOne(new QueryWrapper<Unit>().eq("name",name));
    }

    /*查询单位*/
    @Override
    public List<Unit> selectUnits(){
        return list();
    }


    /*增加或者修改单位*/
    @Override
    public boolean saveOrUpdateUnit(Unit unit){
        return saveOrUpdate(unit);
    }

    /*删除单位*/
    @Override
    public boolean deleteUnitById(Integer uid){
        return remove(new QueryWrapper<Unit>().eq("uid",uid));
    }





}
