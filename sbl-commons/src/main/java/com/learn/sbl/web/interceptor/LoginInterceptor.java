package com.learn.sbl.web.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.sbl.result.ResponseInfo;
import com.learn.sbl.web.HttpContext;
import com.learn.sbl.web.annotation.Login;
import com.learn.sbl.web.contants.WebConstants;
import com.learn.sbl.web.utils.WebUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author HuXinsheng
 */
public class LoginInterceptor implements HandlerInterceptor {
    private List<String> excludedUrls;
    private List<String> ajaxExcludedUrls;

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    public void setAjaxExcludedUrls(List<String> ajaxExcludedUrls) {
        this.ajaxExcludedUrls = ajaxExcludedUrls;
    }
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HttpContext.setCurrentRequest(request);
            boolean requireLogin;
            HandlerMethod method = (HandlerMethod) handler;
            requireLogin = method.getBeanType().getAnnotation(Login.class) != null;
            if (!requireLogin) {
                requireLogin = method.getMethodAnnotation(Login.class) != null;
            }
            if (!requireLogin) {
                MethodParameter[] parameters = method.getMethodParameters();
                for (MethodParameter param : parameters) {
                    if (param.hasParameterAnnotation(Login.class)) {
                        requireLogin = true;
                        break;
                    }
                }
            }
            if (requireLogin) {
                Object userId = WebUtil.getShiroSession().getAttribute(WebConstants._USER);
                if (userId == null) {
                    ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
                    if (responseBody == null) {
                        String loginUrl = request.getContextPath() + "/login";
                        response.sendRedirect(loginUrl);
                    } else {
                        ObjectMapper mapper = new ObjectMapper();
                        ResponseInfo responseInfo = new ResponseInfo() {
                            @Override
                            public String getCode() {
                                return "100000";
                            }

                            @Override
                            public String getMessage() {
                                return "会话已过期，请重新登录";
                            }
                        };
                        response.setCharacterEncoding("utf-8");
                        mapper.writeValue(response.getWriter(), responseInfo);
                    }
                    return false;
                }
            } else {
                Object userId = WebUtil.getShiroSession().getAttribute(WebConstants._USER);
                if (userId == null) {
                    // 得到请求URL
                    String url = request.getRequestURI();
                    url = url.replace(request.getContextPath(), "");
                    if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                        for (String urls : ajaxExcludedUrls) {
                            if (url.contains(urls)) {
                                return true;
                            }
                        }
                        //如果是ajax请求响应头会有，x-requested-with
                        //在响应头设置session状态
                        response.setHeader("sessionstatus", "timeout");
                        ObjectMapper mapper = new ObjectMapper();
                        ResponseInfo responseInfo = new ResponseInfo() {
                            @Override
                            public String getCode() {
                                return "100000";
                            }

                            @Override
                            public String getMessage() {
                                return "会话已过期，请重新登录";
                            }
                        };
                        response.setCharacterEncoding("utf-8");
                        mapper.writeValue(response.getWriter(), responseInfo);
                    } else {
                        for (String urls : excludedUrls) {
                            if (url.contains(urls)) {
                                return true;
                            }
                        }
                        response.sendRedirect(request.getContextPath() + "/login");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}
