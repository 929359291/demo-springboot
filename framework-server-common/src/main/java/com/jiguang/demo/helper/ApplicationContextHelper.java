package com.jiguang.demo.helper;

import com.jiguang.demo.constants.ApplicationConstant;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author liups
 * @create 2017/12/14
 */
public class ApplicationContextHelper implements ApplicationContextAware {

    public static ApplicationContext context;

    public static ApplicationConstant constant;

    private static final ApplicationContextHelper INSTANCE = new ApplicationContextHelper();

    private ApplicationContextHelper(){}

    public static ApplicationContextHelper getInstance() {
        return INSTANCE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        constant = applicationContext.getBean(ApplicationConstant.class);
    }
}
