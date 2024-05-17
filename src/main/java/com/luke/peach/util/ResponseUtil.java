package com.luke.peach.util;

import com.alibaba.fastjson2.JSONObject;
import com.luke.peach.common.IStatus;
import com.luke.peach.exception.BaseException;
import com.luke.peach.vo.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author ：luke
 * @date ：Created in 2024/4/29 5:24 PM
 * @description：通用工具类
 * @modified By：
 */

@Slf4j
public class ResponseUtil {
    /**
     * 往 response 写出 json
     *
     * @param response 响应
     * @param status   状态
     * @param data     返回数据
     */
    public static void renderJson(HttpServletResponse response, IStatus status, Object data) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);

            response.getWriter().write(JSONObject.toJSONString(ApiResponse.ofStatus(status, data)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }

    /**
     * 往 response 写出 json
     *
     * @param response  响应
     * @param exception 异常
     */
    public static void renderJson(HttpServletResponse response, BaseException exception) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);

            response.getWriter().write(JSONObject.toJSONString(ApiResponse.ofException(exception)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }
}
