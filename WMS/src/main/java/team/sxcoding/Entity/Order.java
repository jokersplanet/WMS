package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("order")
public class Order{
    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("goods_name")
    private String goodsName;

    @TableField("time")
    private String time;

    @TableField("value")
    private BigDecimal value;

    @TableField("count")
    private Integer count;

    @TableField("price")
    private BigDecimal price;

    @TableField("notes")
    private String notes;

    @TableField("status")
    private Integer status;

    @TableField(exist = false)
    private String name;
}
