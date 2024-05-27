package com.luke.peach.config;

import com.luke.peach.filter.AuthenticationTokenFilter;
import com.luke.peach.util.RequestIgnoreUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableConfigurationProperties(CustomConfig.class)
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{

    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    private RequestIgnoreUtil requestIgnoreUtil;
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(requestIgnoreUtil::checkIgnores);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
                //.requestMatchers("/api/auth/login").permitAll()
                // RBAC 动态 url 认证
                .anyRequest().authenticated()
        );
        // Session 管理，因为使用了JWT，所以这里不管理Session
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 异常处理
        //http.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler));
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new DBUserDetailsManager();
//    }

}
