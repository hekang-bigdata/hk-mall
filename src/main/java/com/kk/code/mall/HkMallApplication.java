package com.kk.code.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author HK
 */
@SpringBootApplication
@MapperScan(value = "com.kk.code.mall.mapper")
public class HkMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkMallApplication.class, args);
    }

}
