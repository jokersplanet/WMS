package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("expenditure")
public class Expenditure {


    @TableId(type = IdType.INPUT)
    private String number;

    @TableField("record_number")
    private String recordNumber;

    @TableField("time")
    private String time;

    @TableField("value")
    private BigDecimal value;

    @TableField("destination")
    private String destination;
}
