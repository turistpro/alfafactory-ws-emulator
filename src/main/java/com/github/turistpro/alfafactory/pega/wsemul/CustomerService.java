package com.github.turistpro.alfafactory.pega.wsemul;

import com.github.javafaker.Faker;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.service.Lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerService implements Lifecycle {

    private Faker faker;

    public void init(ServiceContext context) {
        //System.out.println("init service");
        ConfigurationContext configContext = context.getConfigurationContext();
        faker = (Faker) configContext.getProperty("faker");
        if(faker == null) {
            faker = new Faker(new Locale("ru"));
            System.out.println("create new faker object.");
            configContext.setProperty("faker", faker);
        }
    }

    public void destroy(ServiceContext context) {
        //System.out.println("destroy service");
    }


    public Customer CustomerInfo(String id){
        return new CustomerFactory(faker).getFakeCustomer();
    }

    public List<Customer> CustomerSearch(String name) {
        CustomerFactory factory = new CustomerFactory(faker);
        List<Customer> results = new ArrayList<Customer>();
        for(int i=0; i<100; i++) {
            results.add(factory.getFakeCustomer());
        }
        return results;
    }

}
