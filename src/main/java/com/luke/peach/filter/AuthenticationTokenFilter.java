//package com.luke.peach.filter;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.luke.peach.service.CustomUserDetailsService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Objects;
//
///**
// * 认证过滤器
// *
// * @author 阿沐 babamu@126.com
// * <a href="https://maku.net">MAKU</a>
// */
//@Component
//@Slf4j
//public class AuthenticationTokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        // 从请求体中获取 JSON 数据
//        String jsonData = new String(request.getInputStream().readAllBytes());
//        // 解析 JSON 数据为 JSONObject
//        JSONObject jsonObject = JSON.parseObject(jsonData);
//        if (Objects.isNull(jsonObject)){
//            chain.doFilter(request, response);
//            return;
//        }
//        // 获取用户名
//        String username = jsonObject.getString("username");
//        // 获取密码
//        String password = jsonObject.getString("password");
//        username = username != null ? username.trim() : "";
//        password = password != null ? password : "";
//
//        //String token = jwtTokenProvider.resolveToken(request);
//        if (!username.equals("")) {
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            chain.doFilter(request, response);
//        } else {
//            // 如果 token 为空或无效,拒绝访问
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Unauthorized");
//            return;
//        }
//        chain.doFilter(request, response);
//    }
//}
