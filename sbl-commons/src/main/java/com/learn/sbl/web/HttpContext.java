package com.learn.sbl.web;

import com.learn.sbl.web.utils.WebUtil;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.session.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author kevin
 */
@Log4j
public class HttpContext {

    private static ApplicationContext currentContext;

    private static ThreadLocal<HttpServletRequest> requestLocal;

    static {
        requestLocal = new ThreadLocal<>();
    }

    static void setApplicationContext(ApplicationContext context) {
        currentContext = context;
    }

    public static void setCurrentRequest(HttpServletRequest request) {
        requestLocal.set(request);
    }

    public static HttpServletRequest getRequest() {
        return requestLocal.get();
    }

    public static ApplicationContext getApplicationContext() {
        return currentContext;
    }

    public static Session getSession() {
        HttpServletRequest request = getRequest();
        return WebUtil.getShiroSession();
    }

    public static <T> T getBean(Class<T> cls) {
        ApplicationContext ctx = getApplicationContext();
        return ctx.getBean(cls);
    }

    public static String getUrlRoot() {
        HttpServletRequest request = getRequest();
        String host = request.getHeader("host");
        String path = request.getContextPath();
        return "https://" + host + (path.length() > 0 ? path : "");
    }

    public static String getContextPath() {
        HttpServletRequest request = getRequest();
        return request.getContextPath();
    }

    public static String getRequestUrl(HttpServletRequest request) {
        String host = request.getHeader("host");
        StringBuffer requestUrl = request.getRequestURL();
        int index = requestUrl.indexOf("://") + 3;
        int index2 = requestUrl.indexOf("/", index);
        requestUrl.replace(index, index2, host);
        String queryString = request.getQueryString();
        if (queryString != null && queryString.length() > 0) {
            requestUrl.append("?").append(queryString);
        }
        String url = requestUrl.toString();
        log.info("request url in =" + url);
        String scheme = url.substring(0, url.indexOf(":"));
        if (!scheme.equals("https")) {
            url = "https" + url.substring(url.indexOf(":"), url.length());
        }
        log.info("request url out =" + url);
        return url;
    }

    /**
     * 获取所有request请求参数key-value
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }
}
