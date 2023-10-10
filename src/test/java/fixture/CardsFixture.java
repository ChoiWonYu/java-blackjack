package fixture;

import static domain.card.Shape.CLOVER;

import domain.card.Card;
import domain.card.Value;
import java.util.List;

public class CardsFixture {

    public static List<Card> pickCards(Value firstValue, Value secondValue) {
        Card firstCard = new Card(firstValue, CLOVER);
        Card secondCard = new Card(secondValue, CLOVER);

        return List.of(firstCard, secondCard);
    }

}
