package com.github.turistpro.alfafactory.pega.wsemul;


import java.util.ArrayList;
import java.util.List;

public class CustomerService extends AbstractService {


    public Customer CustomerInfo(String id){
        Customer cus = new CustomerFactory(faker).getFakeCustomer();
        if(id != null && !id.isEmpty())
            cus.setId(id);
        return cus;
    }

    public List<Customer> CustomerSearch(Customer cus) {
        CustomerFactory factory = new CustomerFactory(faker);
        Customer genCus;
        List<Customer> results = new ArrayList<>();
        for(int i=0; i<100; i++) {
            genCus = factory.getFakeCustomer(cus);
            results.add(genCus);
        }
        return results;
    }

}
