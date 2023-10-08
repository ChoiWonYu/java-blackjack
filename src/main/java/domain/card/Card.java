package domain.card;

public class Card {

    private final Value value;
    private final Shape shape;

    public Card(final Value value, final Shape shape) {
        this.value = value;
        this.shape = shape;
    }

    public int getNumber() {
        return value.getPrimaryValue();
    }

    public String getDisplay() {
        return value.getDisplay();
    }

    public String getShape() {
        return shape.getShape();
    }

    public boolean isAceCard() {
        return value.isAce();
    }

    public void changeAceValue() {
        value.changePrimaryValue();
    }
}
