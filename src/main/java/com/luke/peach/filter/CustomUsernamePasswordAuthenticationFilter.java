package com.luke.peach.filter;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ：luke
 * @date ：Created in 2024/5/17 6:24 PM
 * @description：
 * @modified By：
 */


public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // 从请求体中获取 JSON 数据
        String jsonData = new String(request.getInputStream().readAllBytes());

        // 解析 JSON 数据为 JSONObject
        JSONObject jsonObject = JSON.parseObject(jsonData);

        // 获取用户名
        String username = jsonObject.getString("username");

        // 获取密码
        String password = jsonObject.getString("password");

        username = username != null ? username.trim() : "";
        password = password != null ? password : "";
        // 创建 UsernamePasswordAuthenticationToken 对象
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        setDetails(request, authRequest);
        return super.getAuthenticationManager().authenticate(authRequest);
    }

}
