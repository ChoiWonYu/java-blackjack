package domain.card;

public enum Value {
    ONE("1", 1, null),
    TWO("2", 2, null),
    THREE("3", 3, null),
    FOUR("4", 4, null),
    FIVE("5", 5, null),
    SIX("6", 6, null),
    SEVEN("7", 7, null),
    EIGHT("8", 8, null),
    NINE("9", 9, null),
    KING("K", 10, null),
    JACK("J", 10, null),
    QUEEN("Q", 10, null),
    ACE("A", 11, 1);

    private String display;
    private Integer primaryValue;
    private Integer secondValue;

    Value(String display, Integer primaryValue, Integer secondValue) {
        this.display = display;
        this.primaryValue = primaryValue;
        this.secondValue = secondValue;
    }

    public String getDisplay() {
        return display;
    }

    public Integer getPrimaryValue() {
        return primaryValue;
    }

    public void changePrimaryValue() {
        Integer tmp = primaryValue;
        primaryValue = secondValue;
        secondValue = tmp;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }
}
