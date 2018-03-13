package com.learn.sbl.web.utils;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class WebUtil {
    public WebUtil() {
    }

    public static Session getShiroSession() {
        return getShiroSubject().getSession();
    }

    public static Subject getShiroSubject() {
        return SecurityUtils.getSubject();
    }

    public static void clearKaptcha() {
        getShiroSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
    }
}
