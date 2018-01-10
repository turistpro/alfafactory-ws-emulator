package com.github.turistpro.alfafactory.pega.wsemul;


import java.util.ArrayList;
import java.util.List;

public class AccountService extends AbstractService {

    public Account AccountInfo(String id){
        return new AccountFactory(faker).getFakeAccount();
    }

    public List<Account> AccountList(String customerId) {
        AccountFactory factory = new AccountFactory(faker);
        Account genAcc;
        List<Account> results = new ArrayList<>();
        int accountNumbers = faker.number().numberBetween(1, 5);
        for(int i = 0; i < accountNumbers; i++) {
            genAcc = factory.getFakeAccount();
            genAcc.setCustomerId(customerId);
            results.add(genAcc);
        }
        return results;
    }

}
