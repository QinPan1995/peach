package com.luke.peach.controller;

import cn.hutool.json.JSONUtil;
import com.luke.peach.common.model.CallbackParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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

    /**
     * 创建飞书事件订阅回调方法
     */
    @RequestMapping(value = "/callback",method = RequestMethod.POST)
    public String callback(CallbackParam callbackParam) {
        log.info("callbackParam：{}", JSONUtil.toJsonStr(callbackParam));
        return callbackParam.getChallenge();
    }

}
