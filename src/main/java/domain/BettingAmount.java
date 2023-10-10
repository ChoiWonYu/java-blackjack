package domain;

public class BettingAmount {

    private static final int MIN_BETTING_AMOUNT=0;

    private final int bettingAmount;

    public BettingAmount(final int bettingAmount) {
        validateAmount(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void validateAmount(final int bettingAmount) {
        if (bettingAmount <= MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException();
        }
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
