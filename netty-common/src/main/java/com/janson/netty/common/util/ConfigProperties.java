package com.janson.netty.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/9/19 3:12 下午
 */
public class ConfigProperties {

    private String propertyFileName = "";
    private Properties properties = new Properties();

    public ConfigProperties() {

    }

    public ConfigProperties(String propertyFileName) {
        this.propertyFileName = propertyFileName;
    }

    protected void loadFromFile() {
        InputStream in = null;
        InputStreamReader inputStreamReader = null;

        try {
            ClassLoader classLoader = ConfigProperties.class.getClassLoader();
            in = classLoader.getResourceAsStream(propertyFileName);
            if (null != in) {
                inputStreamReader = new InputStreamReader(in, "utf-8");
            } else {
                String filePath = IOUtil.getResourcePath(propertyFileName);
                in = new FileInputStream(filePath);
                inputStreamReader = new InputStreamReader(in, "utf-8");
            }
            properties.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(inputStreamReader);
        }
    }

    /**
     * 按key获取值
     *
     * @param key
     * @return
     */
    public String readProperty(String key) {
        String value = "";
        value = properties.getProperty(key);
        return value;
    }

    public String getValue(String key) {
        return readProperty(key);
    }

    public int getIntValue(String key) {
        return Integer.parseInt(readProperty(key));
    }

    public static ConfigProperties loadFromFile(Class aClass) {
        ConfigProperties propertiesUtil = null;
        return propertiesUtil;
    }

}