package com.reallct.seabank;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan
@EnableScheduling
@SpringBootApplication(scanBasePackages="com.reallct.seabank")
@MapperScan("com.reallct.seabank.mapper")

public class SeabankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeabankApplication.class, args);
    }

}
