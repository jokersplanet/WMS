package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.OutboundRecords;
import team.sxcoding.Entity.ScrapRecords;

import java.time.LocalDateTime;
import java.util.List;

public interface ScrapRecordsService extends IService<ScrapRecords> {

    /*查询所有报废记录*/
    IPage<ScrapRecords> selectScrapRecords(Integer page, Integer count);

    List<ScrapRecords> selectScrapRecords();

    /*根据时间查询报废记录*/
    IPage<ScrapRecords> selectScrapRecordsByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime);

    /*报废记录是否存在*/
    boolean isExistScrapRecords(String uid);

    /*新建或修改报废记录*/
    boolean saveOrUpdateScrapRecords(ScrapRecords scrapRecords);

    /*返回插入或修改后的本条数据*/
    ScrapRecords selectScrapRecordsById(String uid);

    /*删除报废记录*/
    boolean deleteScrapRecordsById(String uid);
}
