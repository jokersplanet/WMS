package team.sxcoding.Mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Goods;
import team.sxcoding.Entity.Order;


@Component
public interface OrderMapper extends BaseMapper<Order> {

    @Select("SELECT order.*, status.name AS name FROM order LEFT JOIN status ON order.status = status.uid WHERE uid = #{uid} ")
    Order selectOrderById(@Param("uid") Integer uid);

    @Select("SELECT order.*, status.name AS name FROM order LEFT JOIN status ON order.status = status.uid WHERE 1 = 1 AND (order.uid = #{uid} OR #{uid} IS NULL) AND (order.status = #{status} OR #{status} IS NULL) AND (order.goods_name LIKE  '%' || #{name} || '%' OR #{name} IS NULL)")
    IPage<Order> selectOrder(@Param("uid") Integer uid,@Param("name") String goodsName,@Param("status")String status,Page<Order> page);

    @Select("SELECT order.*, status.name AS name FROM order LEFT JOIN status ON order.status = status.uid ")
    IPage<Order> selectAllOrder(Page<Order> page);

    @Select("SELECT order.*, status.name AS name FROM order LEFT JOIN status ON order.status = status.uid " +
            "WHERE 1 = 1 AND (order.uid = #{uid} OR #{uid} IS NULL) AND (order.status = #{status} OR #{status} IS NULL) AND (order.goods_name LIKE  '%' || #{name} || '%' OR #{name} IS NULL" +
            "AND (order.time BETWEEN #{startTime} AND #{endTime}  OR #{startTime} IS NULL OR #{endTime} IS NULL)")
    IPage<Order> selectOrderByTime(@Param("uid")Integer uid,@Param("name")String goodsName,@Param("status")String status,@Param("startTime")String startTime,@Param("endTime")String endTime,Page<Order> page);


    @Select("SELECT order.*, status.name AS name FROM order LEFT JOIN status ON order.status = status.uid ORDER BY order.uid DESC LIMIT 1")
    Order getLastestOrder();
}
