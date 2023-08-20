package team.sxcoding.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import team.sxcoding.Entity.Goods;
import team.sxcoding.Entity.User;

import java.time.LocalDateTime;

@Component
public interface GoodsMapper extends BaseMapper<Goods> {


    @Select("SELECT goods.*, warehouse.name AS warehouse_name,class.name AS class_name, `group`.name AS group_name,unit.name AS unit_name FROM goods LEFT JOIN warehouse ON goods.warehouse_uid = warehouse.uid " +
            "LEFT JOIN  `group` ON goods.group_uid =  `group`.uid LEFT JOIN class ON goods.class_uid = class.uid LEFT JOIN unit ON goods.unit_uid = unit.uid " +
            "WHERE 1 = 1 AND (goods.uid = #{uid} OR #{uid} IS NULL) AND (class_uid = #{classUid} OR #{classUid} IS NULL) AND (group_uid = #{groupUid} OR #{groupUid} IS NULL)" +
            "AND (warehouse_uid = #{warehouseUid} OR #{warehouseUid} IS NULL) AND (goods.name LIKE  '%' || #{name} || '%' OR #{name} IS NULL)")
    IPage<Goods> selectPage(Page<Goods> page,Integer uid,String name ,Integer classUid, Integer groupUid ,Integer warehouseUid);


    @Select("SELECT goods.*, warehouse.name AS warehouse_name,class.name AS class_name, `group`.name AS group_name,unit.name AS unit_name FROM goods LEFT JOIN warehouse ON goods.warehouse_uid = warehouse.uid " +
            "LEFT JOIN `group` ON goods.group_uid = `group`.uid LEFT JOIN class ON goods.class_uid = class.uid LEFT JOIN unit ON goods.unit_uid = unit.uid " +
            "WHERE 1 = 1 AND (goods.uid = #{uid} OR #{uid} IS NULL) AND (class_uid = #{classUid} OR #{classUid} IS NULL) AND (group_uid = #{groupUid} OR #{groupUid} IS NULL)" +
            "AND (warehouse_uid = #{warehouseUid} OR #{warehouseUid} IS NULL) AND (goods.name LIKE  '%' || #{name} || '%' OR #{name} IS NULL)" +
            "AND (inbound_time between #{start} AND #{end} OR #{start} IS NULL OR #{end} IS NULL)")
    IPage<Goods> selectPageByInboundTime(Page<Goods> page, Integer uid,String name ,Integer classUid, Integer groupUid ,Integer warehouseUid, @Param("start") String start,@Param("end") String end);

    @Select("SELECT goods.*, warehouse.name AS warehouse_name,class.name AS class_name, `group`.name AS group_name,unit.name AS unit_name FROM goods LEFT JOIN warehouse ON goods.warehouse_uid = warehouse.uid " +
            "LEFT JOIN `group` ON goods.group_uid = `group`.uid LEFT JOIN class ON goods.class_uid = class.uid LEFT JOIN unit ON goods.unit_uid = unit.uid " +
            "WHERE 1 = 1 AND (goods.uid = #{uid} OR #{uid} IS NULL) AND (class_uid = #{classUid} OR #{classUid} IS NULL) AND (group_uid = #{groupUid} OR #{groupUid} IS NULL)" +
            "AND (warehouse_uid = #{warehouseUid} OR #{warehouseUid} IS NULL) AND (goods.name LIKE  '%' || #{name} || '%' OR #{name} IS NULL)" +
            "AND (outbound_time between #{start} AND #{end} OR #{start} IS NULL OR #{end} IS NULL)")
    IPage<Goods> selectPageByOutboundTime(Page<Goods> page,Integer uid,String name ,Integer classUid, Integer groupUid ,Integer warehouseUid, String start,String end);


}
