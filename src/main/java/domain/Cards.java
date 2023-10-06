package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {

    public static List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards=cards;
    }

    public static Cards createDefaultCards() {
        List<Card> defaultCards = new ArrayList<>();

        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] shapes=new String[]{"클로버","스페이드","하트","다이아몬드"};
        for (String shape : shapes) {
            for (int num : numbers) {
                defaultCards.add(new Card(num,shape));
            }
        }
        return new Cards(defaultCards);
    }

    public List<Card> getCardsByCardCount(final int cardCount) {
        List<Card> pickedCards = new ArrayList<>();
        for (int i = 0; i < cardCount; i++) {
            Card pickedCard=getCard();
            pickedCards.add(pickedCard);
        }
        return pickedCards;
    }

    public Card getCard() {
        Random random = new Random();

        int cardsSize= cards.size();
        int cardIndex = random.nextInt(cardsSize);

        return cards.remove(cardIndex);
    }
}
