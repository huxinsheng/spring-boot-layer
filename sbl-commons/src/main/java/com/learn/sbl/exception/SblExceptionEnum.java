package com.learn.sbl.exception;


import com.learn.sbl.result.ResponseInfo;

/**
 * 所有业务异常的枚举
 *
 * @author HuXinsheng
 */
public enum SblExceptionEnum implements ResponseInfo {
    /**
     * 账号问题
     */
    USER_ALREADY_REG("40001", "该用户已经注册"),
    USER_NOT_EXISTED("40002", "没有此用户"),
    USER_PWD_ERROR("40003", "密码不正确");

    private String code;
    private String message;

    SblExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

