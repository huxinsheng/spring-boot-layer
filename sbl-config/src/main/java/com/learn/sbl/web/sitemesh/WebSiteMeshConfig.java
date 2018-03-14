package com.learn.sbl.web.sitemesh;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.learn.sbl.env.EnvContextConfig;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kevin
 */
@Configuration
public class WebSiteMeshConfig extends ConfigurableSiteMeshFilter {

    private List<String> excluedPaths = new ArrayList<>();

    private static final String _CONFIG = "sbl.sitemesh.excluedpath";

    public void setExcluedPaths() {
        String excluedPath = EnvContextConfig.get(_CONFIG);
        excluedPath = excluedPath + ",/login,/index,/error/*";
        if (StringUtils.isNotEmpty(excluedPath)) {
            excluedPaths = Arrays.asList(excluedPath.split(","));
        }
    }

    public WebSiteMeshConfig() {
        setExcluedPaths();
    }

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addTagRuleBundles(new ExtHtmlRuleBundle());
        //include 请求
        builder.addDecoratorPath("/*", "/decorator");
        for (String excluedPath : excluedPaths) {
            builder.addExcludedPath(excluedPath);
        }
    }
}
