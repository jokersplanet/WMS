package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Goods;

import java.time.LocalDateTime;
import java.util.List;

public interface GoodsService extends IService<Goods> {

    boolean isExistClass(Integer uid);

    boolean isExistGroup(Integer uid);

    boolean isExistWarehouse(Integer uid);

    boolean isExistUnity(Integer uid);


    IPage<Goods> selectGoods(Goods goods,Integer page,Integer count);

    IPage<Goods> selectGoodsByInboundTime(Goods goods, LocalDateTime start, LocalDateTime end,Integer page,Integer count);

    IPage<Goods> selectGoodsByOutboundTime(Goods goods, LocalDateTime start,LocalDateTime end,Integer page,Integer count);



    boolean saveOrUpdateGoods(Goods goods);

    boolean isExistGoods(String uid);

    boolean deleteGoodsById(String uid);

    List<Goods> selectGoods();

    Goods selectGoodsById(String uid);



}
