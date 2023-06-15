package team.sxcoding.Utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NextIdUtil {

    @Autowired
    private static BaseMapper baseMapper;

    private static final String DATE_FORMAT = "yyyyMMdd";

    public  static <T> String nextId(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String dateStr = dateFormat.format(now);

        String maxId = getMaxIdFromDatabase(dateStr);

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

    public static <T> String getMaxIdFromDatabase(String dateStr) {


        QueryWrapper<T>  queryWrapper = new QueryWrapper<>();
        queryWrapper.like("uid",dateStr).orderByDesc("uid").last("LIMIT 1");


        T entity = (T) baseMapper.selectOne(queryWrapper);


        if ( entity != null ) {
            try {
                Method method = entity.getClass().getMethod("getUid");
                return (String) method.invoke(entity);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
        // 示例：假设数据库查询结果为 maxId
    }


}
