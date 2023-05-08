package com.kk.code.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kk.code.mall.common.utlis.JwtTokenUtil;
import com.kk.code.mall.entity.UmsAdmin;
import com.kk.code.mall.entity.UmsPermission;
import com.kk.code.mall.mapper.UmsAdminMapper;
import com.kk.code.mall.mapper.UmsAdminRoleRelationMapper;
import com.kk.code.mall.service.IUmsAdminService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.service.impl
 * @ClassName: UmsAdminServiceImpl
 * @Author: HK
 * @Description:
 * @Date: 2023/5/5 17:37
 * @Version: 1.0
 */

@Service
@Log4j2
public class UmsAdminServiceImpl implements IUmsAdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UmsAdminMapper adminMapper;
    @Resource
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {


        LambdaQueryWrapper<UmsAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsAdmin::getUsername, username);
        List<UmsAdmin> adminList = adminMapper.selectList(wrapper);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(LocalDateTime.now());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户


        LambdaQueryWrapper<UmsAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UmsAdmin::getUsername, umsAdmin.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectList(wrapper);

        if (adminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationMapper.getPermissionList(adminId);
    }
}
