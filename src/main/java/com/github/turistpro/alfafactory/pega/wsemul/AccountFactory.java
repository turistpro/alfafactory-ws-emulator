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
    private static final int[] ACCOUNT_CONTROL_NUMBERS = {7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1};

    public AccountFactory(Faker faker) {
        this.faker = faker;
    }

    private String genAccountNumber(String currencyCode) {
        String accountNumber;

        if(faker.random().nextBoolean()) {
            accountNumber = "40817";
        } else {
            accountNumber = "40820";
        }
        accountNumber += currencyCode;
        accountNumber += "0";
        accountNumber += Long.toString(faker.number().numberBetween(100, 400));
        accountNumber += String.format("%08d", faker.number().numberBetween(100, 499000));
        int checksum = accountCheckSum(accountNumber, "044525593");
        accountNumber = accountNumber.substring(0, 8) + String.valueOf(checksum) + accountNumber.substring(9);

        return accountNumber;
    }

    private int accountCheckSum(String accountNumber, String bik) {
        int sum = 0;
        String num23 = bik.substring(bik.length()-4, bik.length()-1) + accountNumber;
        int length = num23.length();
        for (int i = 0; i < length; i++) {
            sum += (num23.charAt(i) - '0') * ACCOUNT_CONTROL_NUMBERS[i];
        }
        return ((sum % 10) * ACCOUNT_CONTROL_NUMBERS[11]) % 10;
    }

    public Account getFakeAccount() {
        Account account = new Account();
        int currencyNumber = faker.number().numberBetween(0,2);
        account.setCurrencyCode(CURRENCY_LIST[currencyNumber]);
        account.setAccountNumber(genAccountNumber(CURRENCY_CODE_LIST[currencyNumber]));
        account.setOpenDate(faker.date().past(365, TimeUnit.DAYS));
        if(faker.number().numberBetween(0,9) > 6) {
            Date closeDate = faker.date().past(90, TimeUnit.DAYS);
            if(closeDate.before(account.getOpenDate())) {
                account.setCloseDate(new Date());
            } else {
                account.setCloseDate(closeDate);
            }
        }
        double price =  20000 + (20000/(0.5 - new Random().nextGaussian()));
        if (price < 1000) {
            price = faker.number().numberBetween(0, 2000000)/100;
        }
        account.setBalance(BigDecimal.valueOf(((double)Math.round(price*100))/100));
        return account;
    }
}
