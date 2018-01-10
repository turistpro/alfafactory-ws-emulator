package com.github.turistpro.alfafactory.pega.wsemul;

import com.github.javafaker.Faker;
import com.github.javafaker.FakerRussian;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.service.Lifecycle;

import java.util.Locale;

public class AbstractService implements Lifecycle{

    protected FakerRussian faker;

    public void init(ServiceContext context) {
        //System.out.println("init service");
        ConfigurationContext configContext = context.getConfigurationContext();
        faker = (FakerRussian) configContext.getProperty("faker");
        if(faker == null) {
            faker = new FakerRussian(new Locale("ru"));
            System.out.println("create new faker object.");
            configContext.setProperty("faker", faker);
        }
    }

    public void destroy(ServiceContext context) {
        //System.out.println("destroy service");
    }

}
