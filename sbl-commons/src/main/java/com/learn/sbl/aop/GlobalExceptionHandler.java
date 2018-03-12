package com.learn.sbl.aop;

import com.learn.sbl.exception.RmsException;
import com.learn.sbl.result.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * 表示 GlobalExceptionHandler 是一个全局的异常处理器.
 *
 * @author HuXinsheng
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handle(Exception e) {
        if (e instanceof RmsException) {
            RmsException rmsException = (RmsException) e;
            return ResultBody.error(rmsException.getResponseInfo());
        } else {
            log.error("【系统异常】{}", e);
            return ResultBody.error("【系统异常】" + e.getMessage());
        }
    }

//    /**
//     * 拦截业务异常
//     */
//    @ExceptionHandler(RmsException.class)
//    @ResponseBody
//    public ResultBody notFount(RmsException e) {
//        return ResultBody.error(e.getResponseInfo());
//    }
}
