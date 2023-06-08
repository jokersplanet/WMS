package team.sxcoding.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Group;
import team.sxcoding.Entity.Unit;

import java.util.List;

public interface UnitService extends IService<Unit> {

    /*判断unit是否存在*/
    boolean isExistUnitId(Integer id);

    boolean isExistUnitName(String name);

    /*查询单位*/
    List<Unit> selectUnits();

    List<Unit> selectUnitIdAndName();

    /*增加单位*/
    boolean insertUnit(Unit unit);

    /*删除单位*/
    boolean deleteUnitById(Integer id);

    /*修改单位*/
    boolean updateUnitById(Unit unit);
}
