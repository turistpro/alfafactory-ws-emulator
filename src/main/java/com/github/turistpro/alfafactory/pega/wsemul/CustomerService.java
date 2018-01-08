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
        Customer cus = new CustomerFactory(faker).getFakeCustomer();
        cus.setId(id);
        return cus;
    }

    public List<Customer> CustomerSearch(Customer cus) {
        CustomerFactory factory = new CustomerFactory(faker);
        Customer genCus;
        List<Customer> results = new ArrayList<Customer>();
        for(int i=0; i<100; i++) {
            genCus = factory.getFakeCustomer(cus);
            results.add(genCus);
        }
        return results;
    }

}
