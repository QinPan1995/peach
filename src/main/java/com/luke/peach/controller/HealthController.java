package com.luke.peach.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active}")
    private String active;
    @RequestMapping("/health")
    public String sayHi() {
        return active+"环境运行"+applicationName+":hello world";
    }

}
