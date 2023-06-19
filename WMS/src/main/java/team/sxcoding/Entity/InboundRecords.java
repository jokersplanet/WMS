package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
@Data
@TableName("inbound_records")
public class InboundRecords {
    @TableId(type = IdType.AUTO)
    private String uid;

    @TableField("time")
    private String time;

    @TableField("goods_uid")
    private String goodsUid;

    @TableField("count")
    private Long count;

    @TableField("notes")
    private String notes;


    @TableField("value")
    private BigDecimal value;

    @TableField("price")
    private BigDecimal price;

    @TableField("supplier")
    private String supplier;

    @TableField("warehouse_number")
    private String warehouseNumber;
}
