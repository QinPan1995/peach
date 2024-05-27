package com.luke.peach.util;


import com.luke.peach.config.CustomConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ：luke
 * @date ：Created in 2024/5/27 10:16 AM
 * @description：
 * @modified By：
 */


@Component
public class RequestIgnoreUtil {

    @Autowired
    private CustomConfig customConfig;

    /**
     * 请求是否不需要进行权限拦截
     *
     * @param request 当前请求
     * @return true - 忽略，false - 不忽略
     */
    public boolean checkIgnores(HttpServletRequest request) {
        String method = request.getMethod();

        HttpMethod httpMethod = HttpMethod.valueOf(method);

        Set<String> ignores = new HashSet<>();

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

        if (!CollectionUtils.isEmpty(ignores)) {
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