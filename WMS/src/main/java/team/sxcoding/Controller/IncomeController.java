package team.sxcoding.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Expenditure;
import team.sxcoding.Entity.Income;
import team.sxcoding.Service.IncomeService;
import team.sxcoding.Service.UserService;
import team.sxcoding.Utils.NextIdUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


import static team.sxcoding.Utils.PermissionUtil.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IncomeService incomeService;


    /*查询所有收入*/
    @GetMapping("getIncome")
    public ServerResponse getIncome(Integer page , Integer count) {
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
        return ServerResponse.Success("查询成功", incomeService.selectIncome(page,count));
    }


    @GetMapping("getIncomeByTime")
    public ServerResponse getIncomeByTime(Integer page , Integer count, LocalDateTime startTime, LocalDateTime endTime){
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
        return ServerResponse.Success("查询成功", incomeService.selectIncomeByTime(page,count,startTime,endTime));
    }

    @PostMapping("updateIncome")
    public ServerResponse updateIncome(@RequestBody Income income){
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

        if(income.getUid() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else if(!incomeService.isExistIncome(income.getUid())){
            return ServerResponse.ErrorMessage("记录不存在");
        }else if(incomeService.saveOrUpdateIncome(income)){
            return ServerResponse.Success(incomeService.selectIncomeById(income.getUid()));
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @GetMapping("deleteIncome")
    public ServerResponse deleteIncome(String uid){
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
        }else if(!incomeService.isExistIncome(uid)){
            return ServerResponse.ErrorMessage("支出记录不存在");
        }else if(incomeService.deleteIncomeById(uid)){
            return ServerResponse.Success("删除成功",incomeService.selectIncome());
        }else{
            return ServerResponse.ErrorMessage("操作失败");
        }
    }

    @PostMapping("insertIncome")
    public ServerResponse insertIncome(@RequestBody Income income){
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

        if(income.getTime() == null || income.getValue() == null){
            return ServerResponse.ErrorMessage("必填字段未填写");
        }else{
            //获取当天的上一个id，然后传入下一个id，开始更新操作最后返回更新后的数据
            income.setUid("P"+ incomeService.getNextId());
            incomeService.saveOrUpdateIncome(income);
            return ServerResponse.Success(incomeService.selectIncomeById(income.getUid()));
        }
    }
}
