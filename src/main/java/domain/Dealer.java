package domain;

import domain.card.Deck;

public class Dealer extends Player {

    private static final int STANDARD_OF_ADDITIONAl_CARD = 17;
    private final static String DEFAULT_DEALER_NAME = "딜러";

    private Dealer(final Name name, final Deck deck) {
        super(name, deck);
    }

    public static Dealer createDefaultDealer() {
        Deck initialDeck = Deck.createDefaultDeck();
        Name initialName = new Name(DEFAULT_DEALER_NAME);
        return new Dealer(initialName, initialDeck);
    }

    public boolean haveToPickMoreCard() {
        return this.getCardSum() < STANDARD_OF_ADDITIONAl_CARD;
    }
}
