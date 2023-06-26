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

    @Select("SELECT goods.*, warehouse.name,class.name, 'group'.name,unit.name FROM goods LEFT JOIN warehouse ON goods.warehouse_uid = warehouse.uid " +
            "LEFT JOIN 'group' ON goods.group_uid = 'group'.uid LEFT JOIN class ON goods.class_uid = class.uid LEFT JOIN unit ON goods.unit_uid = unit.uid " +
            "WHERE 1 = 1 AND (uid = #{uid} OR #{uid} IS NULL) AND (class_uid = #{classUid} OR #{classUid} IS NULL) AND (group_uid = #{groupUid} OR #{groupUid} IS NULL)" +
            "AND (warehouse_uid = #{warehouseUid} OR #{warehouseUid} IS NULL) AND (name LIKE  '%' || #{name} || '%' OR #{name} IS NULL)")
    IPage<Goods> selectPage(Page<Goods> page, Goods goods);


    @Select("SELECT goods.*, warehouse.name,class.name, 'group'.name,unit.name FROM goods LEFT JOIN warehouse ON goods.warehouse_uid = warehouse.uid " +
            "LEFT JOIN 'group' ON goods.group_uid = 'group'.uid LEFT JOIN class ON goods.class_uid = class.uid LEFT JOIN unit ON goods.unit_uid = unit.uid " +
            "WHERE 1 = 1 AND (uid = #{uid} OR #{uid} IS NULL) AND (class_uid = #{classUid} OR #{classUid} IS NULL) AND (group_uid = #{groupUid} OR #{groupUid} IS NULL)" +
            "AND (warehouse_uid = #{warehouseUid} OR #{warehouseUid} IS NULL) AND (name LIKE  '%' || #{name} || '%' OR #{name} IS NULL)" +
            "AND (inbound_time between #{start} AND #{end} OR #{start} IS NULL OR #{end} IS NULL)")
    IPage<Goods> selectPageByInboundTime(Page<Goods> page, Goods goods, LocalDateTime start,LocalDateTime end);

    @Select("SELECT goods.*, warehouse.name,class.name, 'group'.name,unit.name FROM goods LEFT JOIN warehouse ON goods.warehouse_uid = warehouse.uid " +
            "LEFT JOIN 'group' ON goods.group_uid = 'group'.uid LEFT JOIN class ON goods.class_uid = class.uid LEFT JOIN unit ON goods.unit_uid = unit.uid " +
            "WHERE 1 = 1 AND (uid = #{uid} OR #{uid} IS NULL) AND (class_uid = #{classUid} OR #{classUid} IS NULL) AND (group_uid = #{groupUid} OR #{groupUid} IS NULL)" +
            "AND (warehouse_uid = #{warehouseUid} OR #{warehouseUid} IS NULL) AND (name LIKE  '%' || #{name} || '%' OR #{name} IS NULL)" +
            "AND (outbound_time between #{start} AND #{end} OR #{start} IS NULL OR #{end} IS NULL)")
    IPage<Goods> selectPageByOutboundTime(Page<Goods> page, Goods goods, LocalDateTime start,LocalDateTime end);


}
