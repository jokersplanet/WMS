package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Class;
import team.sxcoding.Entity.Group;
import team.sxcoding.Entity.Unit;
import team.sxcoding.Mapper.UnitMapper;
import team.sxcoding.Service.UnitService;

import java.util.List;

@Service("UnitService")
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements UnitService {


    /*判断Unit是否存在*/
    @Override
    public boolean isExistUnitId(Integer id){
        if(count(new QueryWrapper<Unit>().eq("id",id))>0){
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

    /*查询单位*/
    @Override
    public List<Unit> selectUnits(){
        return list();
    }

    @Override
    public List<Unit> selectUnitIdAndName(){
        return baseMapper.selectList(new QueryWrapper<Unit>().select("id","name"));
    }

    /*增加或者修改单位*/
    @Override
    public boolean saveOrUpdateUnit(Unit unit){
        return saveOrUpdate(unit);
    }

    /*删除单位*/
    @Override
    public boolean deleteUnitById(Integer id){
        return removeById(id);
    }





}
