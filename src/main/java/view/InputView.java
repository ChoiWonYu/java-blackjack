package view;

import java.util.Scanner;

public class InputView {

    private final Scanner reader;

    public InputView(Scanner reader) {
        this.reader = reader;
    }

    public String getInputLine() {
        return reader.nextLine();
    }

    public int getInteger() {
        return reader.nextInt();
    }
}
