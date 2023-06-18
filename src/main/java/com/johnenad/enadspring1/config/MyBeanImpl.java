package com.johnenad.enadspring1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyBeanImpl implements MyBean {

    private String message1;
    private String value1;

    public MyBeanImpl(@Value("${app.message1}") String message1, @Value("${app.value1}") String value1) {
        this.message1 = message1;
        this.value1 = value1;
    }

    public String getMessage1() {
        return message1;
    }

    public String getValue1() {
        return value1;
    }

    @Override
    public String doSomething() {
        String result = getClass() + ".doSomething() - " + this.getMessage1() + "=" + this.getValue1();
        return result;
    }
}