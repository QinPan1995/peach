package com.luke.peach.peach.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>
 * 定时任务配置，配置线程池，使用不同线程执行任务，提升效率
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-22 19:02
 */
@Configuration
@EnableScheduling
public class TaskConfig {

}
