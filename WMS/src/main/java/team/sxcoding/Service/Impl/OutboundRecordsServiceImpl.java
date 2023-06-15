package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import team.sxcoding.Entity.OutboundRecords;
import team.sxcoding.Mapper.OutboundRecordsMapper;
import team.sxcoding.Service.OutboundRecordsService;

@Service("OutboundRecordsService")
public class OutboundRecordsServiceImpl extends ServiceImpl<OutboundRecordsMapper, OutboundRecords> implements OutboundRecordsService {
}
