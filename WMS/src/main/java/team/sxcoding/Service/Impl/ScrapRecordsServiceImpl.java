package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.OutboundRecords;
import team.sxcoding.Entity.ScrapRecords;
import team.sxcoding.Mapper.ScrapRecordsMapper;
import team.sxcoding.Service.ScrapRecordsService;

import java.time.LocalDateTime;
import java.util.List;

@Service("ScrapRecordsService")
public class ScrapRecordsServiceImpl extends ServiceImpl<ScrapRecordsMapper, ScrapRecords> implements ScrapRecordsService {
    /*查询所有报废记录*/
    @Override
    public IPage<ScrapRecords> selectScrapRecords(Integer page, Integer count){
        return page(new Page<>(page,count));
    }

    @Override
    public List<ScrapRecords> selectScrapRecords(){
        return list();
    }

    /*根据时间查询报废记录*/
    @Override
    public IPage<ScrapRecords> selectScrapRecordsByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime){
        return page(new Page<>(page, count),new QueryWrapper<ScrapRecords>().between("time",startTime,endTime));
    }

    /*入库记录是否存在*/
    @Override
    public boolean isExistScrapRecords(String uid){
        if(count(new QueryWrapper<ScrapRecords>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    /*新建或修改入库记录*/
    @Override
    public boolean saveOrUpdateScrapRecords(ScrapRecords scrapRecords){
        return saveOrUpdate(scrapRecords);
    }

    /*返回插入或修改后的本条数据*/
    @Override
    public ScrapRecords selectScrapRecordsById(String uid){
        return getById(uid);
    }

    /*删除报废记录*/
    @Override
    public boolean deleteScrapRecordsById(String uid){
        return removeById(uid);
    }
}
