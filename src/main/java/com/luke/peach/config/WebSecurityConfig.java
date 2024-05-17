package com.luke.peach.config;

import com.luke.peach.manager.DBUserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(o->o.disable());
        http.cors(o->o.disable());
        http
                //开启授权
                .authorizeHttpRequests(authorize -> authorize
                        //对所有请求开启授权
                        .anyRequest()
                        //已认证的用户自动授权
                        .authenticated()
                )
                //使用表单授权
                .formLogin(withDefaults());
                //使用基本授权，默认提供登录夜
                //.httpBasic(withDefaults());

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        manager.createUser(User.builder().username("user").password("123").passwordEncoder(encoder::encode).roles("USER").build());
//        return manager;
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        DBUserDetailsManager manager = new DBUserDetailsManager();
        return manager;
    }
}
