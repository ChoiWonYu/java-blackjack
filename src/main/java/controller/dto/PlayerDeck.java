package controller.dto;

public class PlayerDeck {

    protected final String name;
    protected final String cards;

    public PlayerDeck(String name, String cards) {
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
