package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {

    @Version
    @TableId(type = IdType.AUTO)
    private Integer uid;

    @TableField("number")
    private String number;

    @TableField("password")
    private String password;

    @TableField("username")
    private String username;

    @TableField("gender")
    private String gender;

    @TableField("photo")
    private String photo;

    @TableField("privilege")
    private String privilege;

    @TableField("birthday")
    private Date birthday;

    @TableField("department")
    private Integer department;

    @TableField("telephone")
    private String telephone;

    @TableField("address")
    private String address;

    @TableField("duties")
    private String duties;


}

