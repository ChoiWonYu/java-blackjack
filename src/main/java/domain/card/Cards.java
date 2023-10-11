package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Cards {

    public static List<Card> cards;

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards createDefaultCards() {
        List<Shape> shapes = List.of(Shape.values());
        List<Value> values = List.of(Value.values());

        List<Card> cards=shapes.stream()
            .flatMap(shape -> values.stream()
                .map(value -> new Card(value, shape)))
            .collect(Collectors.toList());
        return new Cards(cards);
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
