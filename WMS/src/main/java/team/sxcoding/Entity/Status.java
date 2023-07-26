package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("status")
public class Status {

    @TableId(type = IdType.AUTO)
    private String uid;

    @TableField("name")
    private String name;

    @TableField("sequence")
    private String sequence;

}
