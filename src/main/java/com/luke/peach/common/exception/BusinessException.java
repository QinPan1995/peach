package com.luke.peach.common.exception;

import com.luke.peach.common.result.IResultCode;
import lombok.Getter;

/**
 * 自定义业务异常
 *
 * @author luke
 * @since 2024/6/11
 */
@Getter
public class BusinessException extends RuntimeException {

    public IResultCode resultCode;

    public BusinessException(IResultCode errorCode) {
        super(errorCode.getMsg());
        this.resultCode = errorCode;
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Throwable cause){
        super(message, cause);
    }

    public BusinessException(Throwable cause){
        super(cause);
    }


}
