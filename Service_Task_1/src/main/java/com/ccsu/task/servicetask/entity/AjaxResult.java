package com.ccsu.task.servicetask.entity;

import lombok.Data;


@Data
public class AjaxResult {
    private int code;
    private String message;
    private Object data;

    public static AjaxResult success(String message) {
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    public static AjaxResult success(String message, Object data) {
        AjaxResult ajaxResult = success(message);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    public static AjaxResult fail(String msg) {
        AjaxResult result = new AjaxResult();
        result.setCode(404);
        result.setMessage(msg);
        return result;
    }

    public static AjaxResult fail(String message, int code) {
        AjaxResult result = fail(message);
        result.setCode(code);
        return result;
    }



}
