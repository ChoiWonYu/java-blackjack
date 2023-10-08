package domain;

public class BettingAmount {

    private final int bettingAmount;

    public BettingAmount(final int bettingAmount) {
        validateAmount(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    private void validateAmount(final int bettingAmount) {
        if (bettingAmount <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public int getBettingAmount() {
        return bettingAmount;
    }
}
