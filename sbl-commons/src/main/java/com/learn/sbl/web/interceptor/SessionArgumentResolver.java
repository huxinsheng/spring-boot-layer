package com.learn.sbl.web.interceptor;

import com.learn.sbl.web.HttpSession;
import com.learn.sbl.web.annotation.Login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

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
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        if (parameter.hasParameterAnnotation(Login.class)) {
            value = session.getAttribute(HttpSession.USER_SESSION_KEY);
        }
        return value;
    }

}
