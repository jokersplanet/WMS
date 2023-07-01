package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("expenditure")
public class Expenditure {

    @TableId(type = IdType.INPUT)
    private String uid;

    @TableField("record_uid")
    private String recordUid;

    @TableField("time")
    private String time;

    @TableField("value")
    private BigDecimal value;

    @TableField("destination")
    private String destination;

    @TableField("notes")
    private String notes;
}
