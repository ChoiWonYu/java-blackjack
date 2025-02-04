package controller.dto;

public class PlayerRevenue {

    private final String name;
    private final double revenue;

    public PlayerRevenue(final String name, final double revenue) {
        this.revenue = revenue;
        this.name = name;
    }

    public double getRevenue() {
        return revenue;
    }

    public String getName() {
        return name;
    }
}
