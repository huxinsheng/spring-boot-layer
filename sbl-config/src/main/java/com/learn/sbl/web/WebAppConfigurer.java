package com.learn.sbl.web;

import com.learn.sbl.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        LoginInterceptor interceptor = new LoginInterceptor();

        List<String> ajaxExcludedUrls = new ArrayList<>();
        ajaxExcludedUrls.add("/core/res");
        ajaxExcludedUrls.add("/login");
        ajaxExcludedUrls.add("/captcha");

        List<String> excludedUrls = new ArrayList<>();
        excludedUrls.add("/core/res");
        excludedUrls.add("/login");
        excludedUrls.add("/captcha");

        interceptor.setAjaxExcludedUrls(ajaxExcludedUrls);
        interceptor.setExcludedUrls(excludedUrls);
        registry.addInterceptor(interceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
