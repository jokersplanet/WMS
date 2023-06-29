package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("outbound_records")
public class OutboundRecords {
    @TableId(type = IdType.AUTO)
    private String uid;

    @TableField("time")
    private LocalDateTime time;

    @TableField("goods_uid")
    private String goodsUid;

    @TableField("goods_name")
    private String goodsName;

    @TableField("count")
    private Long count;

    @TableField("name")
    private String name;


    @TableField("value")
    private BigDecimal value;

    @TableField("price")
    private BigDecimal price;

    @TableField("username")
    private String username;

    @TableField("user_number")
    private String userNumber;

    @TableField("warehouse_uid")
    private String warehouseUid;
}
