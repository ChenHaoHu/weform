package com.hcy.core.common.response;

public enum RespCode {
    WRONG(2,"请求不存在"),
    ERROR(1,"订单不存在"),
    SUCCESS(0, "请求成功"),
    WARN(-1, "网络异常，请稍后重试"),
    LOGINWRONG(-2, "获取token失败");

    private int code;
    private String msg;

    RespCode(int code, String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}