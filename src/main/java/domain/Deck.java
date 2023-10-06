package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final List<Card> deck;

    private Deck(List<Card> deck) {
        this.deck = deck;
    }

    public static Deck createDefaultDeck() {
        return new Deck(new ArrayList<>());
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public void addCards(List<Card> cards) {
        deck.addAll(cards);
    }

    public int getSum() {
        int deckSum = deck.stream()
            .mapToInt(Card::getNumber)
            .sum();

        return deckSum;
    }

    public List<String> getCardInfo() {
        return deck.stream()
            .map(card ->
                card.getNumber() + card.getShape()
            )
            .collect(Collectors.toList());
    }
}
