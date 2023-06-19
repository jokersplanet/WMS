package team.sxcoding.Controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.InboundRecords;
import team.sxcoding.Service.InboundRecordsService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/inbound")
public class InboundRecordsController {


    @Autowired
    UserService userService;

    @Autowired
    InboundRecordsService inboundRecordsService;

    @Autowired
    private HttpServletRequest request;



    /*查询所有入库记录*/
    @GetMapping("getInboundRecords")
    public ServerResponse getInboundRecords(Integer page , Integer count) {
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
        return ServerResponse.Success("查询成功", inboundRecordsService.selectExpenditure(page,count));
    }


    @GetMapping("getInboundRecordsByTime")
    public ServerResponse getInboundRecordsByTime(Integer page , Integer count, LocalDateTime startTime, LocalDateTime endTime){
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
        return ServerResponse.Success("查询成功", InboundRecordsService.selectInboundRecordsByTime(page,count,startTime,endTime));
    }

    @PostMapping("updateInboundRecords")
    public ServerResponse updateInboundRecords(@RequestBody InboundRecords inboundRecords){
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

        if(!isWarehousekeeper(claims)){
            return ServerResponse.Forbidden();
        }

        if(inboundRecords.getUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!InboundRecordsService.isExistInboundRecords(inboundRecords.getUid())){
            return ServerResponse.ErrorMessage("记录不存在");
        }else if(InboundRecordsService.saveOrUpdateInboundRecords(inboundRecords)){
            return ServerResponse.Success(InboundRecordsService.selectInboundRecordsById(inboundRecords.getUid()));
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @GetMapping("deleteInboundRecords")
    public ServerResponse deleteInboundRecords(String uid){
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

        if(!isWarehousekeeper(claims)){
            return ServerResponse.Forbidden();
        }

        if( uid == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!inboundRecordsService.isExistInboundRecords(uid)){
            return ServerResponse.ErrorMessage("支出记录不存在");
        }else if(inboundRecordsService.deleteInboundRecordsById(uid)){
            return ServerResponse.Success("删除成功",inboundRecordsService.selectInboundRecords());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @PostMapping("insertInboundRecords")
    public ServerResponse insertInboundRecords(@RequestBody InboundRecords inboundRecords){
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

        if(!isWarehousekeeper(claims)){
            return ServerResponse.Forbidden();
        }

        if(inboundRecords.getTime() == null || InboundRecords.getValue() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else{
            //获取当天的上一个id，然后传入下一个id，开始更新操作最后返回更新后的数据
            expenditure.setUid("L"+ expenditureService.getNextId());
            expenditureService.saveOrUpdateExpenditure(expenditure);
            return ServerResponse.Success(expenditureService.selectExpenditureById(expenditure.getUid()));
        }
    }


}
