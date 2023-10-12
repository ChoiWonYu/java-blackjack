package domain;

import controller.dto.PlayerRevenue;

public class GameResult {

    private final Revenue revenue;
    private final Name playerName;

    private GameResult(final Name name, final Revenue revenue) {
        this.revenue = revenue;
        this.playerName = name;
    }

    public static GameResult createDefault(Name playerName) {
        Revenue initialRevenue = Revenue.createDefaultRevenue();
        return new GameResult(playerName, initialRevenue);
    }

    public boolean hasSameName(Name name) {
        return playerName.isSameName(name.getName());
    }

    public void lostRevenue(double amount) {
        revenue.lostAmount(amount);
    }

    public void winRevenue(double amount) {
        revenue.winAmount(amount);
    }

    public PlayerRevenue toDto() {
        String name = playerName.getName();
        double resultRevenue = revenue.getRevenue();

        return new PlayerRevenue(name, resultRevenue);
    }

    public double getRevenueValue() {
        return revenue.getRevenue();
    }
}
