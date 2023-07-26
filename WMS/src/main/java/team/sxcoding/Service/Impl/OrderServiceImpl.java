package team.sxcoding.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import team.sxcoding.Entity.Order;
import team.sxcoding.Mapper.OrderMapper;
import team.sxcoding.Service.OrderService;

import java.util.List;


@Service("OrderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Override
    public boolean isExistStatus(Integer uid) {
        if(baseMapper.isExistStatus(uid)>0){
            return true;
        }
        return false;
    }

    @Override
    public Order selectOrderById(Integer uid) {
        return baseMapper.selectOrderById(uid);
    }

    @Override
    public IPage<Order> selectOrder(Integer uid, String goodsName,String status, Integer page, Integer count) {
        return baseMapper.selectOrder(uid,goodsName,status,new Page<>(page,count));
    }

    @Override
    public IPage<Order> selectOrderByTime(Integer uid, String goodsName,String status, String startTime, String endTime, Integer page, Integer count) {
        return baseMapper.selectOrderByTime(uid,goodsName, status,startTime,endTime,new Page<>(page,count));
    }

    @Override
    public IPage<Order> selectOrder(Integer page, Integer count) {
        return baseMapper.selectAllOrder(new Page<>(page,count));
    }


    @Override
    public Order selectOrderLimit() {
        return baseMapper.getLastestOrder();
    }

    @Override
    public boolean deleteOrderById(Integer uid) {
        return baseMapper.deleteOrderById(uid);
    }

    @Override
    public boolean isExistOrder(Integer uid) {
        if(baseMapper.isExistOrder(uid)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        return baseMapper.updateOrder(order);
    }

    @Override
    public boolean insertOrder(Order order) {
        return baseMapper.insertOrder(order);
    }


}
