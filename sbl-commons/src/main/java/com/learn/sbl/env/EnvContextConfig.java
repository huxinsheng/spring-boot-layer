package com.learn.sbl.env;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnvContextConfig {
    private static Map<String, String> configHolder = new ConcurrentHashMap();

    public EnvContextConfig() {
    }

    public static String get(String key) {
        return (String)configHolder.get(key);
    }

    public static String get(String key, Object defaultVal) {
        return !configHolder.containsKey(key) ? "" + defaultVal : (String)configHolder.get(key);
    }

    public static void put(String key, String val) {
        if (val != null) {
            val = val.trim();
        }

        configHolder.put(key, val);
    }

    public static String remove(String key) {
        return (String)configHolder.remove(key);
    }

    public static String getAppName() {
        return get("app.name");
    }

    public static String getAppEnv() {
        return get("env");
    }
}
