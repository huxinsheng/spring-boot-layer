package com.learn.sbl.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 返回体
 *
 * @author HuXinsheng
 */
public class ResultBody {

    /**
     * 响应代码
     */
    private static final String CODE = "code";

    /**
     * 响应消息
     */
    private static final String MESSAGE = "message";

    /**
     * 响应结果
     */
    private static final String DATA = "data";

    public static Object success() {
        return result(ResponseInfoEnum.SUCCESS, null);
    }

    public static Object success(Object data) {
        return result(ResponseInfoEnum.SUCCESS, data);
    }

    public static Object success(ResponseInfo response) {
        return result(response, null);
    }

    public static Object error() {
        return result(ResponseInfoEnum.ERROR, null);
    }

    public static Object error(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE, ResponseInfoEnum.ERROR.getCode());
        jsonObject.put(MESSAGE, message);
        return jsonObject;
    }

    public static Object error(ResponseInfo response) {
        return result(response, null);
    }

    public static Object result(ResponseInfo response, Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE, response.getCode());
        jsonObject.put(MESSAGE, response.getMessage());
        jsonObject.put(DATA, data);
        return jsonObject;
    }
}
