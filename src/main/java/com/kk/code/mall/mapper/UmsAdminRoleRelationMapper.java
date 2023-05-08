package com.kk.code.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kk.code.mall.entity.UmsAdminRoleRelation;
import com.kk.code.mall.entity.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author hk
 * @since 2023-05-05
 */
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {

    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
