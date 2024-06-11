package com.luke.peach.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 * 定时任务配置，配置线程池，使用不同线程执行任务，提升效率
 * </p>
 *
 * @author luke
 * @since 2024/6/11
 */
@Configuration
@EnableScheduling
public class TaskConfig {

}
