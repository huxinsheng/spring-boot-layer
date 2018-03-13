package com.learn.sbl;

import com.learn.sbl.env.EnvContextConfig;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletContext;

/**
 * @author HuXinsheng
 * @since 2018/2/23
 */
@Configuration
public class AdminWebMVCConfiguration implements ServletContextInitializer {


    @Override
    public void onStartup(ServletContext servletContext) {
        servletContext.setAttribute("_title", EnvContextConfig.get("sbl.admin.title"));
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

            container.addErrorPages(error404Page, error500Page);
        });
    }

}
