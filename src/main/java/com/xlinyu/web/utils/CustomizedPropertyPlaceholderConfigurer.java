package com.xlinyu.web.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	
	private static Logger logger = Logger.getLogger(CustomizedPropertyPlaceholderConfigurer.class);
	
	private Map<String, String> ctxPropertiesMap = null;
	
	/**
	 * 初始化属性文件
	 */
	@Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            logger.info("-------->>>>>" + keyStr + "====" + value);
            ctxPropertiesMap.put(keyStr, value);
        }
    }
	
	/**
	 * 获取属性值
	 * @param name
	 * @return
	 */
    public String getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
}
