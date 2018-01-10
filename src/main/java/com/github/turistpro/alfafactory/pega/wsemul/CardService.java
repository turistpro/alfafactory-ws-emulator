package com.github.turistpro.alfafactory.pega.wsemul;


import java.util.ArrayList;
import java.util.List;

public class CardService extends AbstractService {

    public Card CardInfo(String id){
        return new CardFactory(faker).getFakeCard();
    }

    public List<Card> CardList(String customerId) {
        CardFactory factory = new CardFactory(faker);
        Card genCard;
        List<Card> results = new ArrayList<>();
        int cardNumbers = faker.number().numberBetween(1, 5);
        for(int i = 0; i < cardNumbers; i++) {
            genCard = factory.getFakeCard();
            genCard.setCustomerId(customerId);
            results.add(genCard);
        }
        return results;
    }

}
