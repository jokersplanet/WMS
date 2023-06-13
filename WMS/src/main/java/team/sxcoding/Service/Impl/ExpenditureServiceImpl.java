package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Expenditure;
import team.sxcoding.Mapper.ExpenditureMapper;
import team.sxcoding.Service.ExpenditureService;

import java.time.LocalDateTime;


@Service("ExpenditureService")
public class ExpenditureServiceImpl extends ServiceImpl<ExpenditureMapper, Expenditure> implements ExpenditureService{

    /*查询所有支出*/
    @Override
    public IPage<Expenditure> selectExpenditure(Integer page,Integer count){
        return page(new Page<>(page,count));
    }

    /*根据时间查询支出*/
    @Override
    public IPage<Expenditure> selectExpenditureByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime){
        return page(new Page<>(page, count),new QueryWrapper<Expenditure>().between("time",startTime,endTime));
    }

}
