package iai.xmu.geek;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: iai.xmu.edu.cn

 */
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
