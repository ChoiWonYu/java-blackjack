package domain;

import java.util.List;

import domain.card.Card;
import domain.card.Deck;

public class Player {

    protected final Name name;
    protected final Deck deck;
    private BettingAmount bettingAmount;

    protected Player(final Name name, final Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    private Player(final Name name, final Deck deck, final BettingAmount bettingAmount) {
        this.name = name;
        this.deck = deck;
        this.bettingAmount = bettingAmount;
    }

    public static Player createDefault(final String name, final int amount) {
        Deck initialDeck = Deck.createDefaultDeck();
        Name playersName = new Name(name);
        BettingAmount bettingAmount = BettingAmount.createDefault(amount);
        return new Player(playersName, initialDeck, bettingAmount);
    }

    public void addCardsToDeck(final List<Card> firstCards) {
        deck.addCards(firstCards);
    }

    public void addCardToDeck(final Card card) {
        deck.addCard(card);
    }

    public boolean isBlackJack() {
        return deck.isBlackJack();
    }

    public boolean isBurst() {
        return deck.isBurst();
    }

    public int getCardSum() {
        return deck.getBestSum();
    }

    public String getNameValue() {
        return name.getName();
    }

    public List<String> getCardInfos() {
        return deck.getCardInfo();
    }

    public boolean hasSameNameValue(final String playerName) {
        return name.isSameName(playerName);
    }

    public int getBettingAmount() {
        return bettingAmount.getBettingAmount();
    }

    public boolean hasBiggerSum(final int cardSum) {
        int myCardSum = getCardSum();
        return myCardSum > cardSum;
    }

    public Name getName() {
        return this.name;
    }
}
