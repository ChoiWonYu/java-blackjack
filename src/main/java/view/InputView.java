package view;

import java.util.Scanner;

public class InputView {

    private final Scanner reader;

    public InputView(final Scanner reader) {
        this.reader = reader;
    }

    public String getInputLine() {
        return reader.nextLine();
    }

    public int getInteger() {
        return Integer.parseInt(reader.nextLine());
    }
}
