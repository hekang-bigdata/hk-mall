package com.kk.code.mall.component.permissions;

import cn.hutool.json.JSONUtil;
import com.kk.code.mall.common.DataResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.component
 * @ClassName: RestAuthenticationEntryPoint
 * @Author: HK
 * @Description: 当未登录或者token失效访问接口时，自定义的返回结果
 * @Date: 2023/5/5 17:28
 * @Version: 1.0
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(DataResult.error(authException.getMessage())));
        response.getWriter().flush();
    }
}