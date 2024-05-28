package com.luke.peach.controller;

import com.luke.peach.request.LoginRequest;
import com.luke.peach.util.JwtUtil;
import com.luke.peach.util.Status;
import com.luke.peach.vo.ApiResponse;
import com.luke.peach.vo.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class TestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${spring.application.name}")
    private String applicationName;
    @RequestMapping("/test")
    public String sayHi() {
        return "hello world "+applicationName;
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
             authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return ApiResponse.of(Status.USERNAME_PASSWORD_ERROR.getCode(),e.getMessage(),e);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.createJWT(authentication, loginRequest.getRememberMe());
        return ApiResponse.ofSuccess(new JwtResponse(jwt));
    }


}
