package com.luke.peach.exception;

import com.luke.peach.util.Status;
import lombok.Getter;

/**
 * <p>
 * 页面异常
 * </p>
 *
 * @author ：luke
 * @date ：Created in 2024/4/26 11:31 AM
 */
@Getter
public class PageException extends BaseException {

    public PageException(Status status) {
        super(status);
    }

    public PageException(Integer code, String message) {
        super(code, message);
    }
}
