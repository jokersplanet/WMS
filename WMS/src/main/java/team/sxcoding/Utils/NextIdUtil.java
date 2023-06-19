package team.sxcoding.Utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class NextIdUtil {



    private static final String DATE_FORMAT = "yyyyMMdd";

    public  static  String getDate(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String dateStr = dateFormat.format(now);
        return dateStr;
        }


        public static String getNextId(String dateStr,String maxId){
            Integer count;
            if(maxId != null){
                String countStr = maxId.substring(maxId.length() - 4);
                count = Integer.parseInt(countStr) + 1;
            } else {
                // 若当天没有记录，则计数位从 1 开始
                count = 1;
            }
            String countStr = String.format("%04d", count);
            // 生成新的 ID
            String newId = dateStr + countStr;
            return newId;
    }

}
