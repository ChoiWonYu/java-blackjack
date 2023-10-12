package service;

import domain.Dealer;
import domain.GameResult;
import domain.GameResults;
import domain.Player;
import domain.Players;

public class RevenueCalculator {

    private final static double BLACK_JACK_DIVIDEND_YIELD = 1.5;

    private final Dealer dealer;
    private final Players players;
    private final GameResults gameResults;

    private RevenueCalculator(final Dealer dealer, final Players players,
        final GameResults results) {
        this.dealer = dealer;
        this.players = players;
        this.gameResults = results;
    }

    public static RevenueCalculator createCalculator(final Dealer dealer, final Players players) {
        GameResults initialResults = GameResults.createDefault();
        GameResult dealerGameResult = GameResult.createDefault(dealer.getName());
        initialResults.addGameResult(dealerGameResult);
        players.getNames()
            .forEach(name -> {
                GameResult result = GameResult.createDefault(name);
                initialResults.addGameResult(result);
            });
        return new RevenueCalculator(dealer, players, initialResults);
    }

    public GameResults calculateRevenues() {
        players.actEachPlayer(this::calculateRevenue);
        return gameResults;
    }

    public void calculateRevenue(Player player) {
        boolean haveToHandleBlackJack = anyoneBlackJack(player);
        boolean haveToHandleBurst = anyoneBurst(player);
        if (haveToHandleBurst) {
            handleBurst(player);
            return;
        }
        if (haveToHandleBlackJack) {
            handleBlackJack(player);
            return;
        }
        handleCommonRevenue(player);
    }

    private void handleCommonRevenue(Player player) {
        boolean isPlayerWin = player.hasBiggerSum(dealer.getCardSum());
        double playerBettingAmount = player.getBettingAmount();

        GameResult playerResult = gameResults.findByName(player.getName());
        GameResult dealerResult = gameResults.findByName(dealer.getName());

        if (isPlayerWin) {
            playerResult.winRevenue(playerBettingAmount);
            dealerResult.lostRevenue(playerBettingAmount);
            return;
        }
        dealerResult.winRevenue(playerBettingAmount);
        playerResult.lostRevenue(playerBettingAmount);
    }

    private void handleBlackJack(Player player) {
        boolean isDealerBlackJack = dealer.isBlackJack();
        boolean isPlayerBlackJack = player.isBlackJack();
        double playerBettingAmount = player.getBettingAmount();

        GameResult playerResult = gameResults.findByName(player.getName());
        GameResult dealerResult = gameResults.findByName(dealer.getName());

        if (bothBlackJack(isDealerBlackJack, isPlayerBlackJack)) {
            return;
        }

        if (isPlayerBlackJack) {
            playerBettingAmount *= BLACK_JACK_DIVIDEND_YIELD;
            playerResult.winRevenue(playerBettingAmount);
            dealerResult.lostRevenue(playerBettingAmount);
            return;
        }
        playerResult.lostRevenue(playerBettingAmount);
        dealerResult.winRevenue(playerBettingAmount);
    }

    private void handleBurst(Player player) {
        boolean isPlayerBurst = player.isBurst();
        double playerBettingAmount = player.getBettingAmount();

        GameResult playerResult = gameResults.findByName(player.getName());
        GameResult dealerResult = gameResults.findByName(dealer.getName());

        if (isPlayerBurst) {
            playerResult.lostRevenue(playerBettingAmount);
            dealerResult.winRevenue(playerBettingAmount);
            return;
        }

        playerResult.winRevenue(playerBettingAmount);
        dealerResult.lostRevenue(playerBettingAmount);
    }

    private boolean bothBlackJack(final boolean isDealerBlackJack,
        final boolean isPlayerBlackJack) {
        return isDealerBlackJack && isPlayerBlackJack;
    }

    private boolean anyoneBlackJack(Player player) {
        return dealer.isBlackJack() || player.isBlackJack();
    }

    private boolean anyoneBurst(Player player) {
        return dealer.isBurst() || player.isBurst();
    }
}
