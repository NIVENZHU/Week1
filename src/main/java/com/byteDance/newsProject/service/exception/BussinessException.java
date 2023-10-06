package com.byteDance.newsProject.service.exception;

/**
 * 业务逻辑报错
 * 输出errorCode和errorMessage
 */
public class BussinessException extends RuntimeException {
    private String errorCode;
    private String msg;

    public BussinessException(String errorCode,String msg){
        super(errorCode+":"+msg);
        this.errorCode = errorCode;
        this.msg=msg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
