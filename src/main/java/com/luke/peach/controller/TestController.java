package com.luke.peach.controller;

import com.alibaba.fastjson2.JSONObject;
import com.luke.peach.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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

    @Value("${spring.application.name}")
    private String applicationName;
    @RequestMapping("/")
    public String sayHi() {
        return "hello world "+applicationName;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
             authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            return ResponseEntity.ok(JSONObject.toJSONString("用户名或密码错误"));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
       return ResponseEntity.ok(JSONObject.toJSONString(loginRequest));
    }


}
