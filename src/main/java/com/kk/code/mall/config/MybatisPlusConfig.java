package com.kk.code.mall.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: hyspgxptkf
 * @Package: com.vdata.cloud.hyspgxptkf.config
 * @ClassName: MybatisPlusConfig
 * @Author: HK
 * @Description:
 * @Date: 2023/4/3 17:54
 * @Version: 1.0
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 注册分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 注册乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
