package com.github.turistpro.alfafactory.pega.wsemul;

import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AccountFactory {

    protected Faker faker;

    private static final String[] CURRENCY_LIST = {"RUR", "USD", "EUR"};
    private static final String[] CURRENCY_CODE_LIST = {"810", "840", "EUR"};

    public AccountFactory(Faker faker) {
        this.faker = faker;
    }

    public Account getFakeAccount() {
        Account account = new Account();
        int currencyNumber = faker.number().numberBetween(0,2);
        account.setCurrencyCode(CURRENCY_LIST[currencyNumber]);
        String accountNumber = "40817";
        accountNumber += CURRENCY_CODE_LIST[currencyNumber];
        accountNumber += Long.toString(faker.number().randomNumber(6, true));
        accountNumber += Long.toString(faker.number().randomNumber(6, true));
        account.setAccountNumber(accountNumber);
        account.setOpenDate(faker.date().past(365, TimeUnit.DAYS));
        if(faker.number().numberBetween(0,9) > 6) {
            Date closeDate = faker.date().past(90, TimeUnit.DAYS);
            if(closeDate.before(account.getOpenDate())) {
                account.setCloseDate(new Date());
            } else {
                account.setCloseDate(closeDate);
            }
        }
        double price =  1000 + (faker.random().nextDouble() * (1000000 - 1000));
        account.setBalance(BigDecimal.valueOf(((double)Math.round(price*100))/100));
        return account;
    }
}
