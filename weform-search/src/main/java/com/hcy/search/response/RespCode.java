package com.hcy.search.response;

public enum RespCode {
    ERROR(1,"请求失败"),
    SUCCESS(0, "请求成功");

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