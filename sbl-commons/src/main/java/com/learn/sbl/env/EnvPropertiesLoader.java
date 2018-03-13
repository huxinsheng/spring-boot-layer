package com.learn.sbl.env;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

public class EnvPropertiesLoader {
    private static Logger logger = LoggerFactory.getLogger("env");
    private static String DEFAULT_CONF_FILE_NAME = "env.properties";
    private static String DEFAULT_CONF_FILE;

    public EnvPropertiesLoader() {
    }

    private static Properties loadLocalFile() {
        Properties filePropIn = new Properties();
        InputStream input = null;

        try {
            input = Thread.currentThread().getContextClassLoader().getResourceAsStream(DEFAULT_CONF_FILE_NAME);
            if (input == null) {
                ResourceBundle res = ResourceBundle.getBundle("env");
                Set<String> keys = res.keySet();
                Iterator var4 = keys.iterator();

                while(var4.hasNext()) {
                    String key = (String)var4.next();
                    filePropIn.put(key, res.getString(key));
                }
            } else {
                filePropIn.load(input);
            }
        } catch (Exception var9) {
            ;
        } finally {
            closeStream(input);
        }

        return filePropIn;
    }

    private static Properties loadOutFile(String filePath) {
        Properties filePropOut = new Properties();
        FileInputStream input = null;

        try {
            input = new FileInputStream(filePath);
            filePropOut.load(input);
            logger.info("外置配置文件{}加载成功", filePath);
        } catch (Exception var7) {
            logger.error("外置配置文件" + filePath + "加载失败！{}", var7.getMessage());
        } finally {
            closeStream(input);
        }

        return filePropOut;
    }

    private static void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException var2) {
                logger.error(var2.getMessage(), var2);
            }
        }

    }

    public static Properties loadFile() {
        logger.info("<<<======开始加载配置文件======>>>");
        Properties properties = new Properties();
        properties.putAll(loadLocalFile());
        if (properties.containsKey("app.name")) {
            String appName = properties.getProperty("app.name");
            DEFAULT_CONF_FILE = File.separator + "wls" + File.separator + "envconfig" + File.separator + appName + File.separator + DEFAULT_CONF_FILE_NAME;
        }

        properties.putAll(loadOutFile(DEFAULT_CONF_FILE));
        properties.entrySet().forEach((value) -> {
            logger.info(value.getKey().toString() + "=" + value.getValue().toString());
            EnvContextConfig.put(value.getKey().toString(), value.getValue().toString());
        });
        return properties;
    }

    static {
        DEFAULT_CONF_FILE = File.separator + "wls" + File.separator + "envconfig" + File.separator + DEFAULT_CONF_FILE_NAME;
    }
}
