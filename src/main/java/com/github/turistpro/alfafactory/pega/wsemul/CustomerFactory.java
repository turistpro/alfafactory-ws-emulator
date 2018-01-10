package com.github.turistpro.alfafactory.pega.wsemul;

import com.github.javafaker.FakerRussian;
import com.github.javafaker.NameRussian;

public class CustomerFactory {

    private FakerRussian faker;

    public CustomerFactory(FakerRussian faker) {
        this.faker = faker;
    }


    public Customer getFakeCustomer() {
        Customer cus = new Customer();
        cus.setId("A" + faker.number().randomNumber(5, true));
        NameRussian fakerName = faker.nameRussian();
        cus.setFullName(fakerName.fullName());
        cus.setLastName(fakerName.lastName());
        cus.setFirstName(fakerName.firstName());
        cus.setMiddleName(fakerName.middleName());
        cus.setPhoneNumber(faker.phoneNumber().phoneNumber().replaceAll("\\D+", ""));
        cus.setBirthdate(faker.date().birthday());
        cus.setEmail(faker.internet().emailAddress(fakerName.username()));
        cus.setInn(faker.russian().inn12());
        return cus;
    }
    public Customer getFakeCustomer(Customer sibCus) {
        return getFakeCustomer();
    }
}
