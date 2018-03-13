package com.learn.sbl.env;

import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class EnvPropertiesSourceLoader extends PropertiesPropertySourceLoader implements PropertySourceLoader {
    public EnvPropertiesSourceLoader() {
    }

    @Override
    public PropertySource<?> load(String name, Resource resource, String profile) throws IOException {
        if (profile == null) {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            Properties env = EnvPropertiesLoader.loadFile();
            env.entrySet().forEach((item) -> {
                properties.put(item.getKey(), item.getValue());
            });
            if (!properties.isEmpty()) {
                return new PropertiesPropertySource(name, properties);
            }
        }

        return null;
    }
}