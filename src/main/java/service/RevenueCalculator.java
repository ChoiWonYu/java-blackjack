package service;

import domain.Dealer;
import domain.Player;

public class RevenueCalculator {

    private final static double BLACK_JACK_DIVIDEND_YIELD=1.5;

    private final Dealer dealer;
    private Player player;

    private RevenueCalculator(final Dealer dealer, final Player player) {
        this.dealer = dealer;
        this.player = player;
    }

    public static RevenueCalculator createCalculatorWithoutPlayer(final Dealer dealer) {
        return new RevenueCalculator(dealer, null);
    }

    private void setPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException();
        }
        this.player=player;
    }

    public void calculateRevenue(Player player) {
        setPlayer(player);

        boolean haveToHandleBlackJack=anyoneBlackJack();
        boolean haveToHandleBurst=anyoneBurst();
        if(haveToHandleBurst){
            handleBurst();
            return;
        }

        if(haveToHandleBlackJack) {
            handleBlackJack();
            return;
        }

        handleCommonRevenue();
    }

    private void handleCommonRevenue() {
        boolean isPlayerWin=player.hasBiggerSum(dealer.getCardSum());
        double playerBettingAmount = player.getBettingAmount();
        if (isPlayerWin) {
            player.winAmount(playerBettingAmount);
            dealer.lostAmount(playerBettingAmount);
            return;
        }
        dealer.winAmount(playerBettingAmount);
        player.lostAmount(playerBettingAmount);
    }

    private void handleBlackJack() {
        boolean isDealerBlackJack = dealer.isBlackJack();
        boolean isPlayerBlackJack = player.isBlackJack();
        double playerBettingAmount = player.getBettingAmount();

        if (bothBlackJack(isDealerBlackJack, isPlayerBlackJack)) {
            return;
        }

        if (isPlayerBlackJack) {
            playerBettingAmount *= BLACK_JACK_DIVIDEND_YIELD;
            player.winAmount(playerBettingAmount);
            dealer.lostAmount(playerBettingAmount);
            return;
        }
        player.lostAmount(playerBettingAmount);
        dealer.winAmount(playerBettingAmount);
    }

    private void handleBurst() {
        boolean isPlayerBurst=player.isBurst();
        double playerBettingAmount = player.getBettingAmount();

        if (isPlayerBurst) {
            player.lostAmount(playerBettingAmount);
            dealer.winAmount(playerBettingAmount);
            return;
        }

        player.winAmount(playerBettingAmount);
        dealer.lostAmount(playerBettingAmount);
    }

    private boolean bothBlackJack(final boolean isDealerBlackJack,
        final boolean isPlayerBlackJack) {
        return isDealerBlackJack && isPlayerBlackJack;
    }

    private boolean anyoneBlackJack() {
        return dealer.isBlackJack()||player.isBlackJack();
    }

    private boolean anyoneBurst() {
        return dealer.isBurst()||player.isBurst();
    }
}
