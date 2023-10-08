package domain;

public class Name {

    private final String name;

    public Name(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isSameName(final String playerName) {
        return name.equals(playerName);
    }

    public boolean hasSameValue(final String nameValue) {
        return name.equals(nameValue);
    }
}
