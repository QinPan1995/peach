package com.luke.peach.exception;

import com.luke.peach.util.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 异常基类
 * </p>
 *
 * @author ：luke
 * @date ：Created in 2024/4/26 11:31 AM
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
    private Integer code;
    private String message;
    private Object data;

    public BaseException() {
    }

    public BaseException(Status status) {
        super(status.getMessage());
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public BaseException(Status status, Object data) {
        this(status);
        this.data = data;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(Integer code, String message, Object data) {
        this(code, message);
        this.data = data;
    }
}
