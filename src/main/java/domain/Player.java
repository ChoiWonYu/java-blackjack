package domain;

import java.util.List;

public class Player {

    protected final int MAX_NUMBER=21;

    protected final String name;
    protected final Deck deck;


    protected Player(String name,Deck deck) {
        this.name=name;
        this.deck=deck;
    }


    public static Player createDefault(final String name) {
        Deck initialDeck=Deck.createDefaultDeck();
        return new Player(name,initialDeck);
    }

    public void addCardToDeck(final List<Card> firstCards) {
        deck.addCards(firstCards);
    }

    public boolean isBlackJack() {
        int deckSum = deck.getSum();
        return deckSum==MAX_NUMBER;
    }

    public boolean isBurst() {
        int deckSum=deck.getSum();
        return deckSum > MAX_NUMBER;
    }

    public int getCardSum() {
        return deck.getSum();
    }
}
