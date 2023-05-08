package com.kk.code.mall.service.impl;

import com.kk.code.mall.common.api.DataResult;
import com.kk.code.mall.service.IUmsMemberService;
import com.kk.code.mall.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.service.impl
 * @ClassName: UmsMemberServiceImpl
 * @Author: HK
 * @Description:
 * @Date: 2023/5/5 17:10
 * @Version: 1.0
 */
@Service
public class UmsMemberServiceImpl implements IUmsMemberService {

    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public DataResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return DataResult.success(sb.toString(), "获取验证码成功");
    }


    //对输入的验证码进行校验
    @Override
    public DataResult verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return DataResult.error("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return DataResult.success(null, "验证码校验成功");
        } else {
            return DataResult.error("验证码不正确");
        }
    }

}
