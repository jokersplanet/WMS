package team.sxcoding.Controller;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.UserService;
import team.sxcoding.Utils.JwtUtil;


import javax.servlet.http.HttpServletRequest;

import static team.sxcoding.Utils.MD5Util.*;
import static team.sxcoding.Utils.JwtUtil.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;


    @PostMapping("/login")
    public ServerResponse login(@RequestBody User user){

        //一次哈希算法加密
         user.setPassword(encrypt(user.getPassword()));
        //采用哈希算法和数据库字段比对，正确登录成功，返回token，错误失败
        if(userService.login(user)){
            user = userService.selectUserByUid(user.getUid());
            String token = generateToken(user.getUid().toString(),user.getPrivilege(),2629746000L);
            return ServerResponse.Success(token);
        }else{
            return ServerResponse.ErrorMessage("用户名不存在或密码不正确");
        }

    }

    @PostMapping("/register")
    public ServerResponse register(@RequestBody User user){

        String jwt = null;
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
        }
        Jws<Claims> claimsJws  = JwtUtil.parseToken(jwt);
        if(!(claimsJws.getBody().getSubject().equals("0")||claimsJws.getBody().getSubject().equals("3"))){
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
