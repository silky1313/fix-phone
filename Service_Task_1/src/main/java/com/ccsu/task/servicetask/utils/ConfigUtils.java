package com.ccsu.task.servicetask.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private static Properties prop = new Properties();

    static {
        try {
            InputStream  is = ConfigUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return prop.getProperty(key);
    }

    public static void main(String[] args) {
        String port = getValue("ip");
        System.out.println(port);
    }
}
