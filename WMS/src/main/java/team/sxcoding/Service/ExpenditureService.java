package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Expenditure;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenditureService extends IService<Expenditure> {

    /*查询所有支出*/
    IPage<Expenditure> selectExpenditure(Integer page, Integer count);

    /*根据时间查询支出*/
    IPage<Expenditure> selectExpenditureByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime);

}
