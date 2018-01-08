package com.github.turistpro.alfafactory.pega.wsemul;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CardFactory {
    protected Faker faker;
    public CardFactory(Faker faker) {
        this.faker = faker;
    }

    public Card getFakeCard() {
        Card card = new Card();
        card.setCardNumber(faker.business().creditCardNumber());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date expireDate = df.parse(faker.business().creditCardExpiry());
            card.setExpireDate(expireDate);
        }
        catch (Exception e) {

        }
        card.setCardType(faker.business().creditCardType());
        return card;
    }
}
