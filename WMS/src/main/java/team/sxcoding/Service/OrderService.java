package team.sxcoding.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import team.sxcoding.Entity.Order;


public interface OrderService extends IService<Order> {

    boolean isExistStatus(Integer uid);

    Order selectOrderById(Integer uid);

    IPage<Order> selectOrder(Integer uid, String goodsName,String status, Integer page, Integer count);

    IPage<Order> selectOrder(Integer page, Integer count);

    boolean deleteOrderById(Integer uid);

    boolean isExistOrder(Integer uid);

    Order selectOrderLimit();

    boolean updateOrder(Order order);

    boolean insertOrder(Order order);
    IPage<Order> selectOrderByTime(Integer uid,String goodsName,String status, String startTime,String endTime,Integer page,Integer count);

}
