package com.luke.peach.config;

import com.luke.peach.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 忽略授权的地址列表
        //List<String> permitList = permitResource.getPermitList();
        //String[] permits = permitList.toArray(new String[0]);

        http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
                // 认证请求
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        // RBAC 动态 url 认证
                        //.anyRequest().access(new CustomAuthorizationManager())
                        // 所有请求都需要登录访问
                        .anyRequest().authenticated()
                )
                // Session 管理，因为使用了JWT，所以这里不管理Session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 异常处理
                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler))
                //.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
