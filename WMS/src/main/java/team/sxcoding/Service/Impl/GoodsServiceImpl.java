package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Goods;
import team.sxcoding.Entity.InboundRecords;
import team.sxcoding.Entity.User;
import team.sxcoding.Mapper.GoodsMapper;
import team.sxcoding.Service.GoodsService;

import java.time.LocalDateTime;
import java.util.List;

@Service("GoodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public boolean isExistClass(Integer uid){
        if(count(new QueryWrapper<Goods>().eq("class_uid",uid))>0){
            return true;
        }
        return false;
    }


    @Override
    public boolean isExistGroup(Integer uid){
        if(count(new QueryWrapper<Goods>().eq("group_uid",uid))>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistWarehouse(Integer uid){
        if(count(new QueryWrapper<Goods>().eq("warehouse_uid",uid))>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistUnity(Integer uid){
        if(count(new QueryWrapper<Goods>().eq("unit_uid",uid))>0){
            return true;
        }
        return false;
    }

    @Override
<<<<<<< HEAD
    public IPage<Goods> selectGoods(Integer uid,String name ,Integer classUid, Integer groupUid ,Integer warehouseUid , Integer page, Integer count){
        return baseMapper.selectPage(new Page<>(page,count),uid,name,classUid,groupUid,warehouseUid);
=======
    public IPage<Goods> selectGoods(Goods goods,Integer page,Integer count){
        return baseMapper.selectPage(new Page<>(page,count),goods);
>>>>>>> 48af4c45d6dd5f2d8f1b0a5d4aa721ff1e11cdc3
    }




    @Override
<<<<<<< HEAD
    public IPage<Goods> selectGoodsByInboundTime(Integer uid,String name ,Integer classUid, Integer groupUid ,Integer warehouseUid, LocalDateTime start, LocalDateTime end,Integer page,Integer count){
        return baseMapper.selectPageByInboundTime(new Page<>(page,count),uid,name,classUid,groupUid,warehouseUid,start,end);
    }

    @Override
    public IPage<Goods> selectGoodsByOutboundTime(Integer uid,String name ,Integer classUid, Integer groupUid ,Integer warehouseUid, LocalDateTime start,LocalDateTime end,Integer page,Integer count){
        return baseMapper.selectPageByOutboundTime(new Page<>(page,count),uid,name,classUid,groupUid,warehouseUid,start,end);
=======
    public IPage<Goods> selectGoodsByInboundTime(Goods goods, LocalDateTime start, LocalDateTime end,Integer page,Integer count){
        return baseMapper.selectPageByInboundTime(new Page<>(page,count),goods,start,end);
    }

    @Override
    public IPage<Goods> selectGoodsByOutboundTime(Goods goods, LocalDateTime start,LocalDateTime end,Integer page,Integer count){
        return baseMapper.selectPageByOutboundTime(new Page<>(page,count),goods,start,end);
>>>>>>> 48af4c45d6dd5f2d8f1b0a5d4aa721ff1e11cdc3
    }


    @Override
    public boolean saveOrUpdateGoods(Goods goods){
        return saveOrUpdate(goods);
    }

    @Override
<<<<<<< HEAD
    public boolean isExistGoods(Integer uid){
=======
    public boolean isExistGoods(String uid){
>>>>>>> 48af4c45d6dd5f2d8f1b0a5d4aa721ff1e11cdc3
        if(count(new QueryWrapper<Goods>().eq("uid",uid))>0){
            return true;
        }
        return false;
    }

    @Override
<<<<<<< HEAD
    public boolean deleteGoodsById(Integer uid){
=======
    public boolean deleteGoodsById(String uid){
>>>>>>> 48af4c45d6dd5f2d8f1b0a5d4aa721ff1e11cdc3
        return removeById(uid);
    }

    @Override
    public List<Goods> selectGoods(){
        return list();
    }

    @Override
<<<<<<< HEAD
    public Goods selectGoodsById(Integer uid){
        return getOne(new QueryWrapper<Goods>().eq("uid",uid));
    }

    @Override
    public boolean isExistGoodsByName(Goods goods){
        if(count(new QueryWrapper<Goods>().eq("name",goods.getName()).eq("warehouse_uid",goods.getWarehouseUid()).eq("group_uid",goods.getGroupUid()).eq("class_uid",goods.getClassUid()))>0){
            return true;
        }
        return false;
    }
=======
    public Goods selectGoodsById(String uid){
        return getOne(new QueryWrapper<Goods>().eq("uid",uid));
    }
>>>>>>> 48af4c45d6dd5f2d8f1b0a5d4aa721ff1e11cdc3
}
