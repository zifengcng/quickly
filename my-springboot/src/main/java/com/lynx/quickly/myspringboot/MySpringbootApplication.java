package com.lynx.quickly.myspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@MapperScan(basePackages = {"com.lynx.quickly.myspringboot.mapper"})
@SpringBootApplication(scanBasePackages = {"com.lynx.quickly.myspringboot"})
public class MySpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringbootApplication.class, args);
    }

}
