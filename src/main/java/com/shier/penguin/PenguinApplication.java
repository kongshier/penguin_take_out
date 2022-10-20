package com.shier.penguin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Shier 2022/9/26
 */

@Slf4j //日志输出注解
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableCaching //开启springCache 功能
public class PenguinApplication {
    public static void main(String[] args) {
        SpringApplication.run(PenguinApplication.class);
        //@Slf4j提供
        log.info("项目启动成功~~~~~");
    }
}
