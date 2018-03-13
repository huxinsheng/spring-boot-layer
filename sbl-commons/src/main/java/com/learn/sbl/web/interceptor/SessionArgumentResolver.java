package com.learn.sbl.web.interceptor;

import com.learn.sbl.web.annotation.Login;
import com.learn.sbl.web.contants.WebConstants;
import com.learn.sbl.web.utils.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * @author kevin
 */
@Component
public class SessionArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Object value = null;
        if (parameter.hasParameterAnnotation(Login.class)) {
            value = WebUtil.getShiroSession().getAttribute(WebConstants._USER);
        }
        return value;
    }

}
