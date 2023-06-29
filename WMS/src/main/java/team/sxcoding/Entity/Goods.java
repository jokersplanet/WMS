package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("goods")
public class Goods {

    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("name")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("inbound_time")
    private String inboundTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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

    @TableField(exist = false)
    private String warehouseName;
    @TableField(exist = false)
    private String className;

    @TableField(exist = false)
    private String groupName;

    @TableField(exist = false)
    private String unitName;

}
