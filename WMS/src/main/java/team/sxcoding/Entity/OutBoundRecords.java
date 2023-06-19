package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
@Data
@TableName("outbound_records")
public class OutboundRecords {
    @TableId(type = IdType.AUTO)
    private String uid;

    @TableField("time")
    private String time;

    @TableField("goods_uid")
    private String goodsUid;

    @TableField("count")
    private Long count;

    @TableField("name")
    private String name;


    @TableField("value")
    private BigDecimal value;

    @TableField("price")
    private BigDecimal price;

    @TableField("custom")
    private String custom;

    @TableField("warehouse_number")
    private String warehouseNumber;
}
