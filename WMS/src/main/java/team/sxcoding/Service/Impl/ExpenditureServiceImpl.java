package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Expenditure;
import team.sxcoding.Mapper.ExpenditureMapper;
import team.sxcoding.Service.ExpenditureService;
import team.sxcoding.Utils.NextIdUtil;

import java.time.LocalDateTime;
import java.util.List;

import static team.sxcoding.Utils.NextIdUtil.getDate;


@Service("ExpenditureService")
public class ExpenditureServiceImpl extends ServiceImpl<ExpenditureMapper, Expenditure> implements ExpenditureService{

    /*查询所有支出*/
    @Override
    public IPage<Expenditure> selectExpenditure(Integer page,Integer count){
        return page(new Page<>(page,count));
    }


    @Override
    public List<Expenditure> selectExpenditure(){
        return list();
    }

    /*根据时间查询支出*/
    @Override
    public IPage<Expenditure> selectExpenditureByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime){
        return page(new Page<>(page, count),new QueryWrapper<Expenditure>().between("time",startTime,endTime));
    }

    /*支出记录是否存在*/
    @Override
    public boolean isExistExpenditure(String uid){
        if(count(new QueryWrapper<Expenditure>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    /*新建或修改支出*/
    @Override
    public boolean saveOrUpdateExpenditure(Expenditure expenditure){
        return saveOrUpdate(expenditure);
    }

    /*返回插入或修改后的本条数据*/
    @Override
    public Expenditure selectExpenditureById(String uid){
        return getById(uid);
    }

    /*删除支出记录*/
    @Override
    public boolean deleteExpenditureById(String uid){
        return removeById(uid);
    }


    /*返回下一个id*/
    @Override
    public String getNextId(){
        String date = getDate();
        Expenditure expenditure = getOne(new QueryWrapper<Expenditure>().like("uid",date).orderByDesc("uid").last("LIMIT 1"));
        String maxId = null;
        if (expenditure != null) {
             maxId = expenditure.getUid();
        }
        return NextIdUtil.getNextId(date,maxId);
    }


}
