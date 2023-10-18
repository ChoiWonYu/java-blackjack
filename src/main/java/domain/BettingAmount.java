package domain;

public class BettingAmount {

    private static final int MIN_BETTING_AMOUNT = 0;

    private final int bettingAmount;

    private BettingAmount(final int bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public static BettingAmount createDefault(final int bettingAmount) {
        validateAmount(bettingAmount);
        return new BettingAmount(bettingAmount);
    }

    private static void validateAmount(final int bettingAmount) {
        if (bettingAmount <= MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException();
        }
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
