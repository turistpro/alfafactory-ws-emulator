package com.github.turistpro.alfafactory.pega.wsemul;

import com.github.javafaker.Faker;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.service.Lifecycle;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardService implements Lifecycle {

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


    public Card CardInfo(String id){
        return new CardFactory(faker).getFakeCard();
    }

    public List<Card> CardList(String customerId) {
        CardFactory factory = new CardFactory(faker);
        Card genCard;
        List<Card> results = new ArrayList<Card>();
        int cardNumbers = faker.number().numberBetween(1, 5);
        for(int i = 0; i < cardNumbers; i++) {
            genCard = factory.getFakeCard();
            genCard.setCustomerId(customerId);
            results.add(genCard);
        }
        return results;
    }

}
