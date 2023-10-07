package domain;

public class Dealer extends Player {

    private final int STANDARD_OF_ADDITIONAl_CARD = 17;

    private Dealer(Name name, Deck deck) {
        super(name, deck);
    }

    public static Dealer createDefaultDealer(final String name) {
        Deck initialDeck = Deck.createDefaultDeck();
        Name initialName = new Name(name);
        return new Dealer(initialName, initialDeck);
    }

    public boolean haveToPickMoreCard() {
        return this.getCardSum() < STANDARD_OF_ADDITIONAl_CARD;
    }
}
