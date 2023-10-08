package controller.dto;

public class PlayerDeck {

    protected final String name;
    protected String cards;

    public PlayerDeck(final String name, final String cards) {
        this.cards = cards;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCards() {
        return cards;
    }
}
