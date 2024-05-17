package com.luke.peach.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author ：luke
 * @date ：Created in 2024/5/15 2:24 PM
 * @description：
 * @modified By：
 */

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CustomConfig.class)
public class SecurityConfig {
//    private final UserDetailsService userDetailsService;
//
//    private final ApplicationEventPublisher applicationEventPublisher;
//
//    @Bean
//    DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        List<AuthenticationProvider> providerList = new ArrayList<>();
//        providerList.add(daoAuthenticationProvider());
//
//        ProviderManager providerManager = new ProviderManager(providerList);
//        providerManager.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(applicationEventPublisher));
//
//        return providerManager;
//    }
}
