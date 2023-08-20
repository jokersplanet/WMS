package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Expenditure;
import team.sxcoding.Entity.InboundRecords;

import java.time.LocalDateTime;
import java.util.List;

public interface InboundRecordsService extends IService<InboundRecords> {

    /*查询所有入库记录*/
    IPage<InboundRecords> selectInboundRecords(Integer page, Integer count);

    List<InboundRecords> selectInboundRecords();

    /*根据时间查询入库记录*/
    IPage<InboundRecords> selectInboundRecordsByTime(Integer page, Integer count, String startTime, String endTime);

    /*入库记录是否存在*/
    boolean isExistInboundRecords(String uid);

    /*新建或修改入库记录*/
    boolean saveOrUpdateInboundRecords(InboundRecords inboundRecords);

    /*返回插入或修改后的本条数据*/
    InboundRecords selectInboundRecordsById(String uid);

    /*删除入库记录*/
    boolean deleteInboundRecordsById(String uid);
}
