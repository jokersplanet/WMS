package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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
        if(baseMapper.isExistUnitId(id)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistUnitName(String name){
        if(baseMapper.isExistUnitName(name)>0){
            return true;
        }
        return false;
    }

    /*查询单位*/
    @Override
    public List<Unit> selectUnits(){
        return baseMapper.selectUnits();
    }

    @Override
    public List<Unit> selectUnitIdAndName(){
        return baseMapper.selectUnitIdAndName();
    }

    /*增加单位*/
    @Override
    public boolean insertUnit(Unit unit){
        return baseMapper.insertUnit(unit);
    }

    /*删除单位*/
    @Override
    public boolean deleteUnitById(Integer id){
        return baseMapper.deleteUnitById(id);
    }

    /*修改单位*/
    @Override
    public boolean updateUnitById(Unit unit){
        return baseMapper.updateUnitById(unit);
    }
}
