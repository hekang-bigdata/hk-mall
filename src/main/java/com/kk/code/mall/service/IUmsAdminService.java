package com.kk.code.mall.service;

import com.kk.code.mall.entity.UmsAdmin;
import com.kk.code.mall.entity.UmsPermission;

import java.util.List;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.service
 * @ClassName: IUmsAdminService
 * @Author: HK
 * @Description:
 * @Date: 2023/5/5 17:39
 * @Version: 1.0
 */
public interface IUmsAdminService {
    UmsAdmin getAdminByUsername(String username);

    UmsAdmin register(UmsAdmin umsAdminParam);

    String login(String username, String password);

    List<UmsPermission> getPermissionList(Long adminId);
}
