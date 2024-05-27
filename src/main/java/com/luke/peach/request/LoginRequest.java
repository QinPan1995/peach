package com.luke.peach.request;

import lombok.Data;

/**
 * @author ：luke
 * @date ：Created in 2024/5/17 4:41 PM
 * @description：
 * @modified By：
 */

@Data
public class LoginRequest {

    private String usernameOrEmailOrPhone;
    private String password;
}
