package domain;

public class Dealer extends Player {

    private final int STANDARD_OF_ADDITIONAl_CARD=17;

    private Dealer(String name,Deck deck) {
        super(name, deck);
    }

    public static Dealer createDefaultDealer(final String name) {
        Deck initialDeck=Deck.createDefaultDeck();
        return new Dealer(name, initialDeck);
    }

    public boolean haveToPickMoreCard() {
        return this.getCardSum()>=STANDARD_OF_ADDITIONAl_CARD;
    }
}
