package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Expenditure;
import team.sxcoding.Entity.Income;

import team.sxcoding.Mapper.IncomeMapper;
import team.sxcoding.Service.IncomeService;

import java.time.LocalDateTime;
import java.util.List;


@Service("IncomeService")
public class IncomeServiceImpl extends ServiceImpl<IncomeMapper, Income> implements IncomeService {

    /*查询所有收入*/
    @Override
    public IPage<Income> selectIncome(Integer page, Integer count){
        return page(new Page<>(page,count));
    }


    @Override
    public List<Income> selectIncome(){
        return list();
    }

    /*根据时间查询支出*/
    @Override
    public IPage<Income> selectIncomeByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime){
        return page(new Page<>(page, count),new QueryWrapper<Income>().between("time",startTime,endTime));
    }

    /*支出记录是否存在*/
    @Override
    public boolean isExistIncome(String uid){
        if(count(new QueryWrapper<Income>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    /*新建或修改支出*/
    @Override
    public boolean saveOrUpdateIncome(Income income){
        return saveOrUpdate(income);
    }

    /*返回插入或修改后的本条数据*/
    @Override
    public Income selectIncomeById(String uid){
        return getById(uid);
    }

    /*删除支出记录*/
    @Override
    public boolean deleteIncomeById(String uid){
        return removeById(uid);
    }

}
