package domain;

public class Dealer extends Player {

    private final static int STANDARD_OF_ADDITIONAl_CARD = 17;
    private final static String DEFAULT_DEALER_NAME = "딜러";

    private Dealer(Name name, Deck deck, Revenue revenue) {
        super(name, deck, revenue);
    }

    public static Dealer createDefaultDealer() {
        Deck initialDeck = Deck.createDefaultDeck();
        Name initialName = new Name(DEFAULT_DEALER_NAME);
        Revenue initialRevenue = Revenue.createDefaultRevenue();
        return new Dealer(initialName, initialDeck,initialRevenue);
    }

    public boolean haveToPickMoreCard() {
        return this.getCardSum() < STANDARD_OF_ADDITIONAl_CARD;
    }
}
