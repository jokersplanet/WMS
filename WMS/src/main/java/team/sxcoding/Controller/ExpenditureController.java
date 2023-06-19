package team.sxcoding.Controller;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Expenditure;
import team.sxcoding.Service.ExpenditureService;
import team.sxcoding.Service.UserService;


import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/expenditure")
public class ExpenditureController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ExpenditureService expenditureService;

    /*查询所有支出*/
    @GetMapping("getExpenditure")
    public ServerResponse getExpenditure(Integer page ,Integer count) {
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
        return ServerResponse.Success("查询成功", expenditureService.selectExpenditure(page,count));
    }


    @GetMapping("getExpenditureByTime")
    public ServerResponse getExpenditureByTime(Integer page , Integer count, LocalDateTime startTime,LocalDateTime endTime){
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
        return ServerResponse.Success("查询成功", expenditureService.selectExpenditureByTime(page,count,startTime,endTime));
    }

    @PostMapping("updateExpenditure")
    public ServerResponse updateExpenditure(@RequestBody Expenditure expenditure){
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

        if(expenditure.getUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!expenditureService.isExistExpenditure(expenditure.getUid())){
            return ServerResponse.ErrorMessage("记录不存在");
        }else if(expenditureService.saveOrUpdateExpenditure(expenditure)){
            return ServerResponse.Success(expenditureService.selectExpenditureById(expenditure.getUid()));
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @GetMapping("deleteExpenditure")
    public ServerResponse deleteExpenditure(String uid){
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
        }else if(!expenditureService.isExistExpenditure(uid)){
            return ServerResponse.ErrorMessage("支出记录不存在");
        }else if(expenditureService.deleteExpenditureById(uid)){
            return ServerResponse.Success("删除成功",expenditureService.selectExpenditure());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @PostMapping("insertExpenditure")
    public ServerResponse insertExpenditure(@RequestBody Expenditure expenditure){
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

        if(expenditure.getTime() == null || expenditure.getValue() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else{
            //获取当天的上一个id，然后传入下一个id，开始更新操作最后返回更新后的数据
               expenditure.setUid("L"+ expenditureService.getNextId());
               expenditureService.saveOrUpdateExpenditure(expenditure);
               return ServerResponse.Success(expenditureService.selectExpenditureById(expenditure.getUid()));
        }
    }



}
