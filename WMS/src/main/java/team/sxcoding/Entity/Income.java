package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("income")
public class Income {
    @TableId(type = IdType.INPUT)
    private String uid;

    @TableField("record_uid")
    private String recordUid;

    @TableField("time")
    private String time;

    @TableField("value")
    private BigDecimal value;

    @TableField("origin")
    private String origin;

    @TableField("notes")
    private String notes;


}
