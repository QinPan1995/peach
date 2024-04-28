//package com.luke.peach.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * @author ：luke
// * @date ：Created in 2024/4/26 3:02 PM
// * @description：Security 配置
// * @modified By：
// */
//
//@Configuration
//@EnableWebSecurity
//@EnableConfigurationProperties(CustomConfig.class)
//public class SecurityConfig{
//
//    @Autowired
//    private CustomConfig customConfig;
//
////    @Autowired
////    private AccessDeniedHandler accessDeniedHandler;
//
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // @formatter:off
////        http.cors(withDefaults())
////                // 关闭 CSRF
////                .csrf(AbstractHttpConfigurer::disable)
////                // 登录行为由自己实现，参考 AuthController#login
////                .formLogin().disable()
////                .httpBasic().disable()
////
////                // 认证请求
////                .authorizeRequests()
////                // 所有请求都需要登录访问
////                .anyRequest()
////                .authenticated()
////                // RBAC 动态 url 认证
////                .anyRequest()
////                .access("@rbacAuthorityService.hasPermission(request,authentication)")
////
////                // 登出行为由自己实现，参考 AuthController#logout
////                .and().logout().disable()
////                // Session 管理
////                .sessionManagement()
////                // 因为使用了JWT，所以这里不管理Session
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////
////                // 异常处理
////                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//        // @formatter:on
//
//        // 添加自定义 JWT 过滤器
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    /**
//     * 放行所有不需要登录就可以访问的请求，参见 AuthController
//     * 也可以在 {@link (HttpSecurity)} 中配置
//     * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}
//     */
////    @Override
////    public void configure(WebSecurity web) {
////        WebSecurity and = web.ignoring().and();
////
////        // 忽略 GET
////        customConfig.getIgnores().getGet().forEach(url -> and.ignoring().requestMatchers(HttpMethod.GET, url));
////
////        // 忽略 POST
////        customConfig.getIgnores().getPost().forEach(url -> and.ignoring().requestMatchers(HttpMethod.POST, url));
////
////        // 忽略 DELETE
////        customConfig.getIgnores().getDelete().forEach(url -> and.ignoring().requestMatchers(HttpMethod.DELETE, url));
////
////        // 忽略 PUT
////        customConfig.getIgnores().getPut().forEach(url -> and.ignoring().requestMatchers(HttpMethod.PUT, url));
////
////        // 忽略 HEAD
////        customConfig.getIgnores().getHead().forEach(url -> and.ignoring().requestMatchers(HttpMethod.HEAD, url));
////
////        // 忽略 PATCH
////        customConfig.getIgnores().getPatch().forEach(url -> and.ignoring().requestMatchers(HttpMethod.PATCH, url));
////
////        // 忽略 OPTIONS
////        customConfig.getIgnores().getOptions().forEach(url -> and.ignoring().requestMatchers(HttpMethod.OPTIONS, url));
////
////        // 忽略 TRACE
////        customConfig.getIgnores().getTrace().forEach(url -> and.ignoring().requestMatchers(HttpMethod.TRACE, url));
////
////        // 按照请求格式忽略
////        customConfig.getIgnores().getPattern().forEach(url -> and.ignoring().requestMatchers(url));
////
////    }
//
//}
