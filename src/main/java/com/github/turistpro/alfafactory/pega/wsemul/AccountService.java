package com.github.turistpro.alfafactory.pega.wsemul;

import com.github.javafaker.Faker;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.service.Lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccountService implements Lifecycle {

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


    public Account AccountInfo(String id){
        return new AccountFactory(faker).getFakeAccount();
    }

    public List<Account> AccountList(String customerId) {
        AccountFactory factory = new AccountFactory(faker);
        Account genAcc;
        List<Account> results = new ArrayList<Account>();
        int accountNumbers = faker.number().numberBetween(1, 5);
        for(int i=0; i<accountNumbers; i++) {
            genAcc = factory.getFakeAccount();
            genAcc.setCustomerId(customerId);
            results.add(genAcc);
        }
        return results;
    }

}
