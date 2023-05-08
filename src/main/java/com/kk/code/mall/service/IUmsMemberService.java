package com.kk.code.mall.service;

import com.kk.code.mall.common.api.DataResult;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.service
 * @ClassName: IUmsMemberService
 * @Author: HK
 * @Description:
 * @Date: 2023/5/5 17:09
 * @Version: 1.0
 */
public interface IUmsMemberService {
    DataResult generateAuthCode(String telephone);

    //对输入的验证码进行校验
    DataResult verifyAuthCode(String telephone, String authCode);
}
