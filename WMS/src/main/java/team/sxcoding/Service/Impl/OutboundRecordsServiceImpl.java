package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import team.sxcoding.Entity.OutboundRecords;
import team.sxcoding.Mapper.OutboundRecordsMapper;
import team.sxcoding.Service.OutboundRecordsService;

import java.time.LocalDateTime;
import java.util.List;

@Service("OutboundRecordsService")
public class OutboundRecordsServiceImpl extends ServiceImpl<OutboundRecordsMapper, OutboundRecords> implements OutboundRecordsService {

    /*查询所有入库记录*/
    @Override
    public IPage<OutboundRecords> selectOutboundRecords(Integer page, Integer count){
        return page(new Page<>(page,count));
    }

    @Override
    public List<OutboundRecords> selectOutboundRecords(){
        return list();
    }

    /*根据时间查询入库记录*/
    @Override
    public IPage<OutboundRecords> selectOutboundRecordsByTime(Integer page, Integer count, String startTime, String endTime){
        return page(new Page<>(page, count),new QueryWrapper<OutboundRecords>().between("time",startTime,endTime));
    }

    /*入库记录是否存在*/
    @Override
    public boolean isExistOutboundRecords(String uid){
        if(count(new QueryWrapper<OutboundRecords>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    /*新建或修改入库记录*/
    @Override
    public boolean saveOrUpdateOutboundRecords(OutboundRecords outboundRecords){
        return saveOrUpdate(outboundRecords);
    }

    /*返回插入或修改后的本条数据*/
    @Override
    public OutboundRecords selectOutboundRecordsById(String uid){
        return getById(uid);
    }

    /*删除入库记录*/
    @Override
    public boolean deleteOutboundRecordsById(String uid){
        return removeById(uid);
    }
}
