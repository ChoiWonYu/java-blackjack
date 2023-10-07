package controller.dto;

public class PlayerDeckResult extends PlayerDeck {

    private final int result;

    public PlayerDeckResult(String name, String cards, int result) {
        super(name, cards);
        this.result = result;
    }

    public static PlayerDeckResult of(PlayerDeck playerDeck, int result) {
        return new PlayerDeckResult(playerDeck.getName(), playerDeck.getCards(), result);
    }

    public int getResult() {
        return result;
    }
}
