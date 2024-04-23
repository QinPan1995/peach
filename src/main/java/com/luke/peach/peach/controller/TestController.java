package com.luke.peach.peach.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;
    @RequestMapping("/")
    public String sayHi() {
        return "hello world "+applicationName;
    }
}
