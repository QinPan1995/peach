package com.luke.peach.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：luke
 * @date ：Created in 2024/4/26 3:03 PM
 * @description：自定义配置
 * @modified By：
 */

@ConfigurationProperties(prefix = "custom.config")
@Data
public class CustomConfig {
    /**
     * 不需要拦截的地址
     */
    private IgnoreConfig ignores;
}
