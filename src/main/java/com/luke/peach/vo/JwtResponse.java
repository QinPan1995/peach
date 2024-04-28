package com.luke.peach.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：luke
 * @date ：Created in 2024/4/26 2:54 PM
 * @description：JWT 响应类
 * @modified By：
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer";

    public JwtResponse(String token) {
        this.token = token;
    }
}
