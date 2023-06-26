package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("goods")
public class Goods {

    @TableId(type = IdType.INPUT)
    private String uid;

    @TableField("name")
    private String name;

    @TableField("inbound_time")
    private String inboundTime;

    @TableField("outbound_time")
    private String outboundTime;

    @TableField("count")
    private Long count;

    @TableField("value")
    private BigDecimal value;

    @TableField("price")
    private BigDecimal price;

    @TableField("lower_limit")
    private Long lowerLimit;

    @TableField("upper_limit")
    private Long upperLimit;


    @TableField("class_uid")
    private Integer classUid;

    @TableField("group_uid")
    private Integer groupUid;

    @TableField("unit_uid")
    private Integer unitUid;

    @TableField("warehouse_uid")
    private Integer warehouseUid;
}
