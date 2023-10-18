package view;

import java.util.Scanner;

public class InputView {

    private static final String NAMES_SPLITTER = ",";

    private final Scanner reader;

    public InputView(final Scanner reader) {
        this.reader = reader;
    }

    public String getInputLine() {
        return reader.nextLine();
    }

    public String[] getSplitNames() {
        String input = getInputLine();
        return input.split(NAMES_SPLITTER);
    }

    public int getInteger() {
        return Integer.parseInt(reader.nextLine());
    }
}
