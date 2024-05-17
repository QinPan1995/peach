package com.luke.peach.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.schedulerx.shade.com.google.common.collect.Sets;
import com.luke.peach.config.CustomConfig;
import com.luke.peach.enums.Status;
import com.luke.peach.exception.SecurityException;
import com.luke.peach.util.JwtUtil;
import com.luke.peach.util.ResponseUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;


/**
 * @author ：luke
 * @date ：Created in 2024/4/26 6:00 PM
 * @description：JWT 认证过滤器
 * @modified By：
 */

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource(name = "customUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomConfig customConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (checkIgnores(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = jwtUtil.getJwtFromRequest(request);

        if (StrUtil.isNotBlank(jwt)) {
            try {
                String username = jwtUtil.getUsernameFromJWT(jwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } catch (SecurityException e) {
                ResponseUtil.renderJson(response, e);
            }
        } else {
            ResponseUtil.renderJson(response, Status.UNAUTHORIZED, null);
        }
    }

    /**
     * 请求是否不需要进行权限拦截
     *
     * @param request 当前请求
     * @return true - 忽略，false - 不忽略
     */
    private boolean checkIgnores(HttpServletRequest request) {
        String method = request.getMethod();

        HttpMethod httpMethod = HttpMethod.valueOf(method);
        if (ObjectUtil.isNull(httpMethod)) {
            httpMethod = HttpMethod.GET;
        }

        Set<String> ignores = Sets.newHashSet();

        if (httpMethod.equals(HttpMethod.GET)) {
            ignores.addAll(customConfig.getIgnores().getGet());
        } else if (httpMethod.equals(HttpMethod.PUT)) {
            ignores.addAll(customConfig.getIgnores().getPut());
        } else if (httpMethod.equals(HttpMethod.HEAD)) {
            ignores.addAll(customConfig.getIgnores().getHead());
        } else if (httpMethod.equals(HttpMethod.POST)) {
            ignores.addAll(customConfig.getIgnores().getPost());
        } else if (httpMethod.equals(HttpMethod.PATCH)) {
            ignores.addAll(customConfig.getIgnores().getPatch());
        } else if (httpMethod.equals(HttpMethod.TRACE)) {
            ignores.addAll(customConfig.getIgnores().getTrace());
        } else if (httpMethod.equals(HttpMethod.DELETE)) {
            ignores.addAll(customConfig.getIgnores().getDelete());
        } else if (httpMethod.equals(HttpMethod.OPTIONS)) {
            ignores.addAll(customConfig.getIgnores().getOptions());
        }

        ignores.addAll(customConfig.getIgnores().getPattern());

        if (CollUtil.isNotEmpty(ignores)) {
            for (String ignore : ignores) {
                AntPathRequestMatcher matcher = new AntPathRequestMatcher(ignore, method);
                if (matcher.matches(request)) {
                    return true;
                }
            }
        }

        return false;
    }

}