package com.luke.peach.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ：luke
 * @date ：Created in 2024/4/26 1:59 PM
 * @description：登录请求参数
 * @modified By：
 */

@Data
public class LoginRequest {
    /**
     * 用户名或邮箱或手机号
     */
    @NotBlank(message = "用户名不能为空")
    private String usernameOrEmailOrPhone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;
}
