package com.learn.sbl.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 *
 * @author King
 * @since 2018/2/28
 */
@Controller
@RequestMapping("/error")
public class AdminErrorController implements ErrorController {

    private static final String PATH_404 = "/404";

    private static final String PATH_500 = "/500";

    private static final String PATH_ERROR = "/error";

    @RequestMapping(value=PATH_404)
    public String handle404(){
        return "_error/404";
    }

    @RequestMapping(value=PATH_500)
    public String handle500(){
        return "_error/500";
    }

    @RequestMapping(value=PATH_ERROR)
    public String handleError(){
        return "_error/error";
    }
    @Override
    public String getErrorPath() {
        return PATH_500;
    }
}
