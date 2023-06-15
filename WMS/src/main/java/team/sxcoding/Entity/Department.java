package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("department")
public class Department {

    @Version
    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("name")
    private String name;

    @TableField("notes")
    private String notes;

}
