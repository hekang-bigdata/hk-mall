package com.kk.code.mall.service;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.service
 * @ClassName: RedisService
 * @Author: HK
 * @Description:
 * @Date: 2023/5/5 17:04
 * @Version: 1.0
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     *
     * @param delta 自增步长
     */
    Long increment(String key, long delta);
}
