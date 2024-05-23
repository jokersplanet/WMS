package team.sxcoding.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.PageResult;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getUserDetailByUid")
    public ServerResponse getUserDetailByUid(Integer uid){
        if (uid == null) {
            return ServerResponse.ErrorMessage("uid为空");
        }
        if(userService.isExistUid(uid)){
            User user = userService.selectUserByUid(uid);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    @GetMapping("getUserDetailByNumber")
    public ServerResponse getUserDetailByNumber(String number){
        if (number.equals(null)) {
            return ServerResponse.ErrorMessage("员工编号为空");
        }
        if(userService.isExistNumber(number)){
            User user = userService.selectUserByNumber(number);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    @GetMapping("getUserDetailByTelephone")
    public ServerResponse getUserDetailByTelephone(String telephone){
        if (telephone.equals(null)) {
            return ServerResponse.ErrorMessage("员工编号为空");
        }
        if(userService.isExistTelephone(telephone)){
            User user = userService.selectUserByTelephone(telephone);
            return ServerResponse.Success("查询成功",user);
        }else {
            return ServerResponse.ErrorMessage("用户不存在");
        }
    }

    @GetMapping("listUsers")
    public ServerResponse listUsers(Integer page,Integer count){
        if ( page == null || count == null) {
            return ServerResponse.Error();
        }else {
            PageResult<User> users = userService.listUsers(page, count);
            return ServerResponse.Success("查询成功", users);
        }
    }

}
