package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.InboundRecords;
import team.sxcoding.Mapper.InboundRecordsMapper;
import team.sxcoding.Service.InboundRecordsService;

import java.time.LocalDateTime;
import java.util.List;

@Service("InboundRecordsService")
public class InboundRecordsServiceImpl extends ServiceImpl<InboundRecordsMapper, InboundRecords> implements InboundRecordsService {

    /*查询所有入库记录*/
    @Override
    public IPage<InboundRecords> selectInboundRecords(Integer page, Integer count){
        return page(new Page<>(page,count));
    }

    @Override
    public List<InboundRecords> selectInboundRecords(){
        return list();
    }

    /*根据时间查询入库记录*/
    @Override
    public IPage<InboundRecords> selectInboundRecordsByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime){
        return page(new Page<>(page, count),new QueryWrapper<InboundRecords>().between("time",startTime,endTime));
    }

    /*入库记录是否存在*/
    @Override
    public boolean isExistInboundRecords(String uid){
        if(count(new QueryWrapper<InboundRecords>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    /*新建或修改入库记录*/
    @Override
    public boolean saveOrUpdateInboundRecords(InboundRecords inboundRecords){
        return saveOrUpdate(inboundRecords);
    }

    /*返回插入或修改后的本条数据*/
    @Override
    public InboundRecords selectInboundRecordsById(String uid){
        return getById(uid);
    }

    /*删除入库记录*/
    @Override
    public boolean deleteInboundRecordsById(String uid){
        return removeById(uid);
    }
}
