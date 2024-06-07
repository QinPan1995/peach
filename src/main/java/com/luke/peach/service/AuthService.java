package com.luke.peach.service;


import com.luke.peach.mode.dto.CaptchaResult;
import com.luke.peach.mode.dto.LoginResult;

/**
 * 认证服务接口
 *
 * @author luke
 * @since 2.4.0
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    LoginResult login(String username, String password);

    /**
     * 登出
     */
    void logout();

    /**
     * 获取验证码
     *
     * @return 验证码
     */
    CaptchaResult getCaptcha();
}
