package com.kk.code.mall.controller;

import com.kk.code.mall.common.DataResult;
import com.kk.code.mall.service.IUmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.controller
 * @ClassName: UmsMemberController
 * @Author: HK
 * @Description:
 * @Date: 2023/5/5 17:08
 * @Version: 1.0
 */
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
@RestController
public class UmsMemberController {
    @Autowired
    private IUmsMemberService memberService;

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public DataResult getAuthCode(@RequestParam String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    public DataResult updatePassword(@RequestParam String telephone,
                                     @RequestParam String authCode) {
        return memberService.verifyAuthCode(telephone, authCode);
    }
}
