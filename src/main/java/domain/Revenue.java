package domain;

public class Revenue {

    private static final Double DEFAULT_REVENUE=0.0;

    private Double revenue;

    private Revenue(Double revenue) {
        this.revenue=revenue;
    }

    public static Revenue createDefaultRevenue() {
        return new Revenue(DEFAULT_REVENUE);
    }

    public void lostAmount(final double amount) {
        this.revenue-=amount;
    }

    public void winAmount(final double amount) {
        this.revenue+=amount;
    }

    public double getRevenue() {
        return revenue;
    }
}
