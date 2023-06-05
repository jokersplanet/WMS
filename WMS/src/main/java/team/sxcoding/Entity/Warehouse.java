package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("warehouse")
public class Warehouse {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("head")
    private String head;

    @TableField("value")
    private BigDecimal value;

    @TableField("notes")
    private String notes;

    @TableField("name")
    private String name;

    @TableField("address")
    private String address;

}
