package com.johnenad.enadspring1.service;

import com.johnenad.enadspring1.config.MyBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service
public class ProdMyServiceImpl implements MyService {

    private MyBean myBean;

    public ProdMyServiceImpl(MyBean myBean) {
        this.myBean = myBean;
    }

    @Override
    public String doSomething() {
        String result = String.format("ProdMyServiceImpl: %s=%s", myBean.getMessage1(), myBean.getValue1());
        // do production-specific stuff here
        System.out.println(result);
        return result;
    }
}
