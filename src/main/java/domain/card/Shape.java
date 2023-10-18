package domain.card;

public enum Shape {

    CLOVER("클로버"),
    SPADE("스페이드"),
    HEART("하트"),
    DIAMOND("다이아몬드");

    private final String shape;

    Shape(final String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
