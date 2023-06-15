package team.sxcoding.Service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.ScrapRecords;
import team.sxcoding.Mapper.ScrapRecordsMapper;
import team.sxcoding.Service.ScrapRecordsService;

@Service("ScrapRecordsService")
public class ScrapRecordsServiceImpl extends ServiceImpl<ScrapRecordsMapper, ScrapRecords> implements ScrapRecordsService {
}
