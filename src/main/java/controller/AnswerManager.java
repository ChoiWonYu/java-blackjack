package controller;

public class AnswerManager {

    private static final String YES_ANSWER="y";
    private static final String NO_ANSWER="n";

    public static boolean validateAnswer(final String answer){
        if (answer.equals(YES_ANSWER)) {
            return true;
        }
        if (answer.equals(NO_ANSWER)) {
            return false;
        }
        throw new IllegalArgumentException();
    }

}
