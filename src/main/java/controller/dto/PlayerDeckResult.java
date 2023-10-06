package controller.dto;

public class PlayerDeckResult extends PlayerDeck {

    private final int result;

    public PlayerDeckResult(String name, String cards, int result) {
        super(name, cards);
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
