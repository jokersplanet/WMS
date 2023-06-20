package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.InboundRecords;
import team.sxcoding.Entity.OutboundRecords;

import java.time.LocalDateTime;
import java.util.List;

public interface OutboundRecordsService extends IService<OutboundRecords> {

    /*查询所有出库记录*/
    IPage<OutboundRecords> selectOutboundRecords(Integer page, Integer count);

    List<OutboundRecords> selectOutboundRecords();

    /*根据时间查询出库记录*/
    IPage<OutboundRecords> selectOutboundRecordsByTime(Integer page, Integer count, LocalDateTime startTime, LocalDateTime endTime);

    /*出库记录是否存在*/
    boolean isExistOutboundRecords(String uid);

    /*新建或修改出库记录*/
    boolean saveOrUpdateOutboundRecords(OutboundRecords outboundRecords);

    /*返回插入或修改后的本条数据*/
    OutboundRecords selectOutboundRecordsById(String uid);

    /*删除出库记录*/
    boolean deleteOutboundRecordsById(String uid);
}
