package team.sxcoding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("team.sxcoding.Mapper")
public class WMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(WMSApplication.class, args);
    }

}
