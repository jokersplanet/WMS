package team.sxcoding.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
public class User {

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
    private String department;

    @TableField("telephone")
    private String telephone;

    @TableField("address")
    private String address;

    @TableField("duties")
    private String duties;


}
