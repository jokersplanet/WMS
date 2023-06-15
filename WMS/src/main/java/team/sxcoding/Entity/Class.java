package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;


@Data
@TableName("class")
public class Class {

    @Version
    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("name")
    private String name;

    @TableField("group_id")
    private Integer groupId;


}
