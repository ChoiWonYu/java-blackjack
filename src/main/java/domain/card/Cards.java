package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {

    public static List<Card> cards;

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createDefaultCards() {
        List<Card> defaultCards = new ArrayList<>();

        Shape[] shapes = Shape.values();
        Value[] values = Value.values();
        for (Shape shape : shapes) {
            for (Value value : values) {
                defaultCards.add(new Card(value, shape));
            }
        }
        return new Cards(defaultCards);
    }

    public List<Card> getCardsByCardCount(final int cardCount) {
        List<Card> pickedCards = new ArrayList<>();
        for (int i = 0; i < cardCount; i++) {
            Card pickedCard = getCard();
            pickedCards.add(pickedCard);
        }
        return pickedCards;
    }

    public Card getCard() {
        Random random = new Random();

        int cardsSize = cards.size();
        int cardIndex = random.nextInt(cardsSize);

        return cards.remove(cardIndex);
    }

    public List<Card> pickInitialCards(final int initialCardsCount) {
        List<Card> pickedCards = new ArrayList<>();
        for (int i = 0; i < initialCardsCount; i++) {
            Card pickedCard = getCard();
            pickedCards.add(pickedCard);
        }
        return pickedCards;
    }
}
