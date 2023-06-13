package team.sxcoding.Controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sxcoding.Config.ServerResponse;
import team.sxcoding.Entity.Class;
import team.sxcoding.Entity.Expenditure;
import team.sxcoding.Service.ExpenditureService;
import team.sxcoding.Service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;

import static team.sxcoding.Utils.PermissionUtil.getToken;
import static team.sxcoding.Utils.PermissionUtil.isJwtLegal;

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
        IPage<Expenditure> expenditureList = expenditureService.selectExpenditure(page,count);
        return ServerResponse.Success("查询成功", expenditureList);
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

        IPage<Expenditure> expenditureList = expenditureService.selectExpenditureByTime(page,count,startTime,endTime);
        return ServerResponse.Success("查询成功", expenditureList);
    }


}
