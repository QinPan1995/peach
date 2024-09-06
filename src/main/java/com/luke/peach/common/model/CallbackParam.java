package com.luke.peach.common.model;

import lombok.Data;

/**
 * @author ：luke
 * @date ：Created in 2024/9/6 15:37
 * @description：
 * @modified By：
 */

@Data
public class CallbackParam {

    private String challenge;
    private String type;
    private String token;
}
