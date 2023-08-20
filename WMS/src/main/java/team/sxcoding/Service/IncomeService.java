package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Expenditure;
import team.sxcoding.Entity.Income;

import java.time.LocalDateTime;
import java.util.List;


public interface IncomeService extends IService<Income> {

    /*查询所有支出*/
    IPage<Income> selectIncome(Integer page, Integer count);

    List<Income> selectIncome();

    /*根据时间查询支出*/
    IPage<Income> selectIncomeByTime(Integer page, Integer count, String startTime, String endTime);

    /*支出记录是否存在*/
    boolean isExistIncome(String uid);

    /*新建或修改支出*/
    boolean saveOrUpdateIncome(Income income);

    /*返回插入或修改后的本条数据*/
    Income selectIncomeById(String uid);

    /*删除支出记录*/
    boolean deleteIncomeById(String uid);

    /*获取下一个id*/
    String getNextId();
}
