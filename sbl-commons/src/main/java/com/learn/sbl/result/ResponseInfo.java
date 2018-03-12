package com.learn.sbl.result;

/**
 * 返回码接口
 * @author HuXinsheng
 */
public interface ResponseInfo {
    /**
     * 获取返回码
     * @return
     */
    String getCode();

    /**
     * 获取返回信息
     * @return
     */
    String getMessage();
}
