package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Expenditure;

import java.time.LocalDateTime;
import java.util.List;


public interface ExpenditureService extends IService<Expenditure> {

    /*查询所有支出*/
    IPage<Expenditure> selectExpenditure(Integer page, Integer count);

    List<Expenditure> selectExpenditure();

    /*根据时间查询支出*/
    IPage<Expenditure> selectExpenditureByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime);

    /*支出记录是否存在*/
    boolean isExistExpenditure(String uid);

    /*新建或修改支出*/
    boolean saveOrUpdateExpenditure(Expenditure expenditure);

    /*返回插入或修改后的本条数据*/
    Expenditure selectExpenditureById(String uid);

    /*删除支出记录*/
    boolean deleteExpenditureById(String uid);

}
