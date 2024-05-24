package com.luke.peach.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 *
 * @author luke
 * @date Created in 2024-05-10 10:29
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return User.builder().username("user").password("123").passwordEncoder(encoder::encode).roles("USER").build();
    }
}
