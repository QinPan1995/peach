package com.luke.peach.exception;

import com.luke.peach.enums.Status;
import lombok.EqualsAndHashCode;

/**
 * @author ：luke
 * @date ：Created in 2024/4/26 1:47 PM
 * @description：全局异常
 * @modified By：
 */

@EqualsAndHashCode(callSuper = true)
public class SecurityException extends BaseException {

    public SecurityException(Status status) {
        super(status);
    }

    public SecurityException(Status status, Object data) {
        super(status, data);
    }

    public SecurityException(Integer code, String message) {
        super(code, message);
    }

    public SecurityException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
