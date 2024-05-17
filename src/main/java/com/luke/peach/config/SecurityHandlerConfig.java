package com.luke.peach.config;

import com.luke.peach.enums.Status;
import com.luke.peach.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author ：luke
 * @date ：Created in 2024/5/15 4:44 PM
 * @description：Security 结果处理配置
 * @modified By：
 */

@Configuration
public class SecurityHandlerConfig {
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> ResponseUtil.renderJson(response, Status.ACCESS_DENIED, null);
    }
}
