package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.InboundRecords;
import team.sxcoding.Mapper.InboundRecordsMapper;
import team.sxcoding.Service.InboundRecordsService;

@Service("InboundRecordsService")
public class InboundRecordsServiceImpl extends ServiceImpl<InboundRecordsMapper, InboundRecords> implements InboundRecordsService {
}
