package com.luke.peach.exception;

import com.luke.peach.util.Status;
import lombok.Getter;

/**
 * <p>
 * JSON异常
 * </p>
 *
 * @author ：luke
 * @date ：Created in 2024/4/26 11:31 AM
 */
@Getter
public class JsonException extends BaseException {

    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
