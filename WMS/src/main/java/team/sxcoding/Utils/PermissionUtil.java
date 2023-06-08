package team.sxcoding.Utils;

import io.jsonwebtoken.Claims;
import team.sxcoding.Config.PermissionLevel;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static team.sxcoding.Config.PermissionLevel.*;

public class PermissionUtil {




    public static Claims getToken(HttpServletRequest request){

        String jwt = null;
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7);
        }
        if(jwt == null){
            return null;
        }
        Claims claims  = JwtUtil.parseToken(jwt);
        return claims;
    }

    public static boolean isJwtLegal(Claims claims,String oldPrivilege){
        Date now = new Date();
        if(claims.getSubject().equals(oldPrivilege) && now.before(claims.getExpiration())){
            return true;
        }
        return false;
    }

    public static boolean isUpdatePermissionIllegal(Claims claims, User user, User oldUser){

        /*
        * 分为两大类
        * 我如果修改自己的信息，那么我无法修改自己的权限(可以修改到低级)
        * 我如果修改别人的信息，那么我无法将别人的权限升级到我的权限到更高权限（管理员）
        * 我如果修改别人的信息，那么我可以将别人的权限升级到我的权限但是不能修改到更高权限，但是之后我无法对他进行操作（用户管理员）*/


         if(claims.getId().equals(user.getUid().toString())){
             if(claims.getSubject().equals(SUPER_ADMIN.getLevel()) && !user.getPrivilege().equals(SUPER_ADMIN.getLevel())){
                 return true;
             }else if( claims.getSubject().equals(ADMIN.getLevel()) && !user.getPrivilege().equals(ADMIN.getLevel())){
                 return true;
             }else if ( claims.getSubject().equals(USER_MANAGER.getLevel()) && (user.getPrivilege().equals(SUPER_ADMIN.getLevel()) || user.getPrivilege().equals(ADMIN.getLevel()))) {
                 return true;
             }else if( !(claims.getSubject().equals(SUPER_ADMIN.getLevel()) || claims.getSubject().equals(ADMIN.getLevel()) || claims.getSubject().equals(USER_MANAGER.getLevel())) && (!claims.getSubject().equals(user.getPrivilege()) || !user.getNumber().equals(oldUser.getNumber()))) {
                 return true;
             }else{
                 return false;
             }
         }else{
             if( !(claims.getSubject().equals(SUPER_ADMIN.getLevel()) || claims.getSubject().equals(ADMIN.getLevel()) || claims.getSubject().equals(USER_MANAGER.getLevel()))){
                 return true;
             }else if( claims.getSubject().equals(SUPER_ADMIN.getLevel()) && user.getPrivilege().equals(SUPER_ADMIN.getLevel()) ){
                 return true;
             }else if( claims.getSubject().equals(ADMIN.getLevel()) && ( oldUser.getPrivilege().equals(SUPER_ADMIN.getLevel()) || oldUser.getPrivilege().equals(ADMIN.getLevel())|| user.getPrivilege().equals(SUPER_ADMIN.getLevel()) || user.getPrivilege().equals(ADMIN.getLevel())) ){
                 return true;
             }else if( claims.getSubject().equals(USER_MANAGER.getLevel()) && ( oldUser.getPrivilege().equals(SUPER_ADMIN.getLevel()) || oldUser.getPrivilege().equals(ADMIN.getLevel()) || oldUser.getPrivilege().equals(USER_MANAGER.getLevel()) || user.getPrivilege().equals(SUPER_ADMIN.getLevel()) || user.getPrivilege().equals(ADMIN.getLevel()))){
                 return true;
             }else if( !(claims.getSubject().equals(SUPER_ADMIN.getLevel()) || claims.getSubject().equals(ADMIN.getLevel()) || claims.getSubject().equals(USER_MANAGER.getLevel()))){
                 return true;
             } else{
                 return false;
             }
         }
    }

    public static boolean isDeletePermissionLegal(Claims claims, User user){

        if(claims.getId().equals(user.getUid().toString())){
            if(claims.getSubject().equals(SUPER_ADMIN.getLevel())) {
                return false;
            }
            return true;
        }else{
            if( !(claims.getSubject().equals(SUPER_ADMIN.getLevel()) || claims.getSubject().equals(ADMIN.getLevel()) || claims.getSubject().equals(USER_MANAGER.getLevel()))) {
                return false;
            }else if(claims.getSubject().equals(ADMIN.getLevel()) && (user.getPrivilege().equals(SUPER_ADMIN.getLevel()) || user.getPrivilege().equals(ADMIN.getLevel()))){
                return false;
            }else if(claims.getSubject().equals(USER_MANAGER.getLevel()) && (user.getPrivilege().equals(SUPER_ADMIN.getLevel()) || user.getPrivilege().equals(ADMIN.getLevel()) || user.getPrivilege().equals(USER_MANAGER.getLevel()))){
                return false;
            }
            return true;
        }
    }

    public  static boolean isAdmin(Claims claims){
        if(claims.getSubject().equals(SUPER_ADMIN.getLevel()) || claims.getSubject().equals(ADMIN.getLevel())){
            return true;
        }
        return false;
    }

    public static boolean isWarehousekeeper(Claims claims){
        if(claims.getSubject().equals(SUPER_ADMIN.getLevel()) || claims.getSubject().equals(ADMIN.getLevel()) || claims.getSubject().equals(WAREHOUSE_KEEPER.getLevel())){
            return true;
        }
        return false;
    }
}
