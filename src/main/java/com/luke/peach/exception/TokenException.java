package com.luke.peach.exception;

import com.luke.peach.util.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 全局异常
 * </p>
 *
 * @author ：luke
 * @date ：Created in 2024/4/26 6:15 PM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TokenException extends BaseException {
    public TokenException(Status status) {
        super(status);
    }

    public TokenException(Status status, Object data) {
        super(status, data);
    }

    public TokenException(Integer code, String message) {
        super(code, message);
    }

    public TokenException(Integer code, String message, Object data) {
        super(code, message, data);
    }
}
