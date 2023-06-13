package team.sxcoding.Config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor(); //配置插件类
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.SQLITE));  //分页
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());    //乐观锁
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());    //防止全更新和全删除
        return interceptor;
    }



}
