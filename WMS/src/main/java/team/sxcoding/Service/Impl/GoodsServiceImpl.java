package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Goods;
import team.sxcoding.Mapper.GoodsMapper;
import team.sxcoding.Service.GoodsService;

@Service("GoodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
