package team.sxcoding.Controller;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.OutboundRecords;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.OutboundRecordsService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/outbound")
public class OutboundRecordsController {

    @Autowired
    UserService userService;

    @Autowired
    OutboundRecordsService outboundRecordsService;

    @Autowired
    private HttpServletRequest request;


    /*查询所有出库记录*/
    @GetMapping("getOutboundRecords")
    public ServerResponse getOutboundRecords(Integer page , Integer count) {
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
        return ServerResponse.Success("查询成功", outboundRecordsService.selectOutboundRecords(page,count));
    }


    @GetMapping("getOutboundRecordsByTime")
    public ServerResponse getOutboundRecordsByTime(Integer page , Integer count, LocalDateTime startTime, LocalDateTime endTime){
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
        return ServerResponse.Success("查询成功", outboundRecordsService.selectOutboundRecordsByTime(page,count,startTime,endTime));
    }

    @PostMapping("updateOutboundRecords")
    public ServerResponse updateOutboundRecords(@RequestBody OutboundRecords outboundRecords){
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

        if(!isAdmin(claims)){
            return ServerResponse.Forbidden();
        }

        if(outboundRecords.getUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!outboundRecordsService.isExistOutboundRecords(outboundRecords.getUid())){
            return ServerResponse.ErrorMessage("记录不存在");
        }else if(outboundRecordsService.saveOrUpdateOutboundRecords(outboundRecords)){
            return ServerResponse.Success(outboundRecordsService.selectOutboundRecordsById(outboundRecords.getUid()));
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @GetMapping("deleteOutboundRecords")
    public ServerResponse deleteOutboundRecords(String uid){
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

        if(!isAdmin(claims)){
            return ServerResponse.Forbidden();
        }

        if( uid == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!outboundRecordsService.isExistOutboundRecords(uid)){
            return ServerResponse.ErrorMessage("支出记录不存在");
        }else if(outboundRecordsService.deleteOutboundRecordsById(uid)){
            return ServerResponse.Success("删除成功",outboundRecordsService.selectOutboundRecords());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @PostMapping("insertOutboundRecords")
    public ServerResponse insertInboundRecords(@RequestBody OutboundRecords outboundRecords){
        Claims claims = null;
        claims = getToken(request);
        User user = new User();
        if (claims.isEmpty()){
            return ServerResponse.NeedLoginMessage("请重新登录");
        } else {
            if (userService.isExistUid(Integer.valueOf(claims.getId()))) {
                user = userService.selectUserByUid(Integer.valueOf(claims.getId()));
                String oldPrivilege = user.getPrivilege();
                if (!isJwtLegal(claims, oldPrivilege)) {
                    return ServerResponse.NeedLoginMessage("请重新登录");
                }
            }else {
                return ServerResponse.NeedLogin();
            }
        }

        if(!isWarehousekeeper(claims)){
            return ServerResponse.Forbidden();
        }

        outboundRecords.setUsername(user.getUsername());
        outboundRecords.setUserNumber(user.getNumber());

        if(outboundRecords.getTime() == null || outboundRecords.getGoodsName() == null || outboundRecords.getCount() == null || outboundRecords.getPrice() == null || outboundRecords.getUsername() == null || outboundRecords.getUserNumber() == null || outboundRecords.getValue() == null || outboundRecords.getWarehouseUid() == null || outboundRecords.getGoodsUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(outboundRecordsService.saveOrUpdateOutboundRecords(outboundRecords)){
            return ServerResponse.Success(outboundRecords);
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

}
