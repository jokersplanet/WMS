package team.sxcoding.Controller;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.UserService;


import javax.servlet.http.HttpServletRequest;

import static team.sxcoding.Config.PermissionLevel.*;
import static team.sxcoding.Utils.MD5Util.*;
import static team.sxcoding.Utils.JwtUtil.*;
import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    /*登录*/
    @PostMapping("/login")
    public ServerResponse login(@RequestBody User user){

        if(user.getPassword() == null || user.getUid() == null){
            return ServerResponse.ErrorMessage("请输入账号密码");
        }
        if(!userService.isExistUid(user.getUid())){
            return ServerResponse.ErrorMessage("用户名不存在");
        }

        //一次哈希算法加密
         user.setPassword(encrypt(user.getPassword()));
        //采用哈希算法和数据库字段比对，正确登录成功，返回token，错误失败
        if(userService.login(user)){
            user = userService.selectUserByUid(user.getUid());
            String token = generateToken(user.getUid().toString(),user.getPrivilege(),26297461000L);
            return ServerResponse.Success(token);
        }else{
            return ServerResponse.ErrorMessage("密码不正确");
        }

    }

    /*注册*/

    @PostMapping("/register")
    public ServerResponse register(@RequestBody User user) {
        Claims claims = null;
        claims = getToken(request);
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                String oldPrivilege = userService.selectUserByUid(Integer.valueOf(claims.getId())).getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }


        if(!((SUPER_ADMIN.getLevel().equals(claims.getSubject()) && ! user.getPrivilege().equals(SUPER_ADMIN.getLevel())) || (ADMIN.getLevel().equals(claims.getSubject()) && !( user.getPrivilege().equals(SUPER_ADMIN.getLevel()) || user.getPrivilege().equals(ADMIN.getLevel()))) || (USER_MANAGER.getLevel().equals(claims.getSubject()) && !( user.getPrivilege().equals(SUPER_ADMIN.getLevel()) || user.getPrivilege().equals(ADMIN.getLevel()) || user.getPrivilege().equals(USER_MANAGER.getLevel()))))){
            return ServerResponse.Forbidden();
        }


        if(user.getUsername().equals(null)||user.getNumber().equals(null)||user.getPassword().equals(null)||user.getGender().equals(null)||user.getPrivilege().equals(null)||user.getTelephone().equals(null)){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(userService.isExistNumber(user.getNumber())||userService.isExistTelephone(user.getTelephone())){
            return ServerResponse.ErrorMessage("员工编号或手机号重复");
        }else{
            user.setPassword(encrypt(user.getPassword()));
            user=userService.register(user);
            return ServerResponse.Success("创建成功",user);
        }

    }


}
