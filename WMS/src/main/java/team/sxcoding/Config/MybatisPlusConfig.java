<<<<<<< HEAD
package team.sxcoding.Config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor(); //配置插件类
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //具体到配置哪一个插件
        return interceptor;
    }

=======
package team.sxcoding.Config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor(); //配置插件类
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //具体到配置哪一个插件
        return interceptor;
    }

>>>>>>> 0be10400409c0210cb86ebb1866173f09c6b461e
}