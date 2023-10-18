package controller.dto;

public class PlayerDeckResult extends PlayerDeck {

    private final int result;

    private PlayerDeckResult(final String name, final String cards, final int result) {
        super(name, cards);
        this.result = result;
    }

    public static PlayerDeckResult of(final PlayerDeck playerDeck, final int result) {
        return new PlayerDeckResult(playerDeck.getName(), playerDeck.getCards(), result);
    }

    public int getResult() {
        return result;
    }
}
