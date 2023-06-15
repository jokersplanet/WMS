package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("unit")
public class Unit {

    @Version
    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("name")
    private String name;

}
