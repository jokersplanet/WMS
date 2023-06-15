package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("group")
public class Group {

    @Version
    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("name")
    private String name;

}
