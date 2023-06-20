package team.sxcoding.Controller;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.OutboundRecords;
import team.sxcoding.Entity.ScrapRecords;
import team.sxcoding.Entity.User;
import team.sxcoding.Service.ScrapRecordsService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/scrap")
public class ScrapRecordsController {

    @Autowired
    UserService userService;

    @Autowired
    ScrapRecordsService scrapRecordsService;

    @Autowired
    private HttpServletRequest request;


    /*查询所有报废记录*/
    @GetMapping("getScrapRecords")
    public ServerResponse getScrapRecords(Integer page , Integer count) {
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
        return ServerResponse.Success("查询成功", scrapRecordsService.selectScrapRecords(page,count));
    }


    @GetMapping("getScrapRecordsByTime")
    public ServerResponse getScrapRecordsByTime(Integer page , Integer count, LocalDateTime startTime, LocalDateTime endTime){
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
        return ServerResponse.Success("查询成功", scrapRecordsService.selectScrapRecordsByTime(page,count,startTime,endTime));
    }

    @PostMapping("updateScrapRecords")
    public ServerResponse updateScrapRecords(@RequestBody ScrapRecords scrapRecords){
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

        if(scrapRecords.getUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!scrapRecordsService.isExistScrapRecords(scrapRecords.getUid())){
            return ServerResponse.ErrorMessage("记录不存在");
        }else if(scrapRecordsService.saveOrUpdateScrapRecords(scrapRecords)){
            return ServerResponse.Success(scrapRecordsService.selectScrapRecordsById(scrapRecords.getUid()));
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @GetMapping("deleteScrapRecords")
    public ServerResponse deleteScrapRecords(String uid){
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
        }else if(!scrapRecordsService.isExistScrapRecords(uid)){
            return ServerResponse.ErrorMessage("支出记录不存在");
        }else if(scrapRecordsService.deleteScrapRecordsById(uid)){
            return ServerResponse.Success("删除成功",scrapRecordsService.selectScrapRecords());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @PostMapping("insertScrapRecords")
    public ServerResponse insertScrapRecords(@RequestBody ScrapRecords scrapRecords){
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

        scrapRecords.setUsername(user.getUsername());
        scrapRecords.setUserNumber(user.getNumber());

        if(scrapRecords.getTime() == null || scrapRecords.getGoodsName() == null || scrapRecords.getCount() == null || scrapRecords.getPrice() == null || scrapRecords.getUsername() == null || scrapRecords.getUserNumber() == null || scrapRecords.getValue() == null || scrapRecords.getWarehouseUid() == null || scrapRecords.getGoodsUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(scrapRecordsService.saveOrUpdateScrapRecords(scrapRecords)){
            return ServerResponse.Success(scrapRecords);
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }
}
