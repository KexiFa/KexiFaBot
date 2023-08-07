package com.kexifa;

import com.zhuangxv.bot.EnableBot;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/3 17:01
 */
@SpringBootApplication
@EnableBot
@EnableScheduling
@MapperScan("com.kexifa.mapper")
public class ExpandServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpandServerApplication.class,args);
    }
}
