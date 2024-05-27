package com.luke.peach.config;

import com.luke.peach.filter.JwtAuthenticationFilter;
import com.luke.peach.manager.RbacAuthorizationManager;
import com.luke.peach.util.RequestIgnoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author ：luke
 * @date ：Created in 2024/4/26 3:02 PM
 * @description：Security 配置
 * @modified By：
 */

@Configuration
@EnableConfigurationProperties(CustomConfig.class)
@EnableWebSecurity
public class WebSecurityFilterConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RbacAuthorizationManager rbacAuthorizationManager;

    @Autowired
    private RequestIgnoreUtil requestIgnoreUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }



    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.anonymous(AbstractHttpConfigurer::disable);
        // 其他安全配置
        http.headers(headers -> headers.httpStrictTransportSecurity(HeadersConfigurer.HstsConfig::disable));
        http.cors(AbstractHttpConfigurer::disable);
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        // 认证请求
        http.authorizeHttpRequests(auth -> auth
                // 放行忽略的请求
                 .requestMatchers("/peach/**", "/login", "/actuator/**", "/error").permitAll()
                // RBAC 动态 url 认证
                .anyRequest().authenticated()
        );
        // Session 管理，因为使用了JWT，所以这里不管理Session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 异常处理
        http.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
