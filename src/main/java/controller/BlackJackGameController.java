package controller;

import static controller.AnswerManager.validateAnswer;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import controller.dto.PlayerRevenue;
import java.util.List;
import java.util.function.Consumer;
import service.BlackJackGame;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    private static final int DEALER_RESULT_DTO_INDEX = 0;

    private final InputView reader;
    private final BlackJackGame game;

    private BlackJackGameController(final InputView reader, final BlackJackGame game) {
        this.reader = reader;
        this.game = game;
    }

    public static BlackJackGameController createDefaultGameController(final InputView reader) {
        BlackJackGame game = BlackJackGame.createDefaultGame();
        return new BlackJackGameController(reader, game);
    }

    public void startGame() {
        getPlayers();
        game.firstDealOutCards();
        showPlayerNames();

        PlayerDeck dealerDeck = game.getDealer();
        OutputView.showDealerDeck(dealerDeck);

        List<PlayerDeck> allPlayers = game.getAllPlayers();
        actEachPlayer(allPlayers, OutputView::showPlayerDeck);
        OutputView.newLine();

        actEachPlayer(allPlayers, this::receiveCards);
        OutputView.newLine();

        checkDealerDeck();
        OutputView.newLine();

        showResult();
        OutputView.newLine();

        OutputView.noticeRevenueDescription();
        List<PlayerRevenue> revenues = game.calculateRevenue();
        actEachPlayer(revenues, OutputView::showRevenues);
    }

    private void showPlayerNames() {
        List<String> playerNames = game.getPlayerNameValues();
        OutputView.noticeFirstDealOut(playerNames);
    }

    private void getPlayers() {
        String[] names = getPlayerNames();
        actEachPlayer(List.of(names), this::getPlayerBettingAmount);
    }

    private String[] getPlayerNames() {
        OutputView.askPlayerNames();
        return reader.getSplitNames();
    }

    private void getPlayerBettingAmount(final String name) {
        OutputView.askPlayerBettingAmount(name);
        int bettingAmount = reader.getInteger();
        game.createGamePlayer(name, bettingAmount);
    }

    private void showResult() {
        PlayerDeckResult dealerResult = game.getDealerResult();
        List<PlayerDeckResult> playerResults = game.getAllPlayerResults();
        playerResults.add(DEALER_RESULT_DTO_INDEX, dealerResult);
        actEachPlayer(playerResults, OutputView::showDeckWithResult);
    }

    private void checkDealerDeck() {
        boolean needMoreCard = game.haveToPickMoreCard();
        if (!needMoreCard) {
            return;
        }
        OutputView.noticeDealerPickedCard();
        game.giveDealerMoreCard();
    }

    private <T> void actEachPlayer(final List<T> allPlayers,
        final Consumer<T> act) {
        allPlayers.forEach(act);
    }

    private void receiveCards(final PlayerDeck player) {
        PlayerDeck currentPlayer = player;
        while (true) {
            OutputView.askMoreCards(player);
            String answer = reader.getInputLine();
            boolean isYes = validateAnswer(answer);
            if (!isYes) {
                OutputView.showPlayerDeck(currentPlayer);
                break;
            }
            currentPlayer = game.giveCardToPlayer(player.getName());
            boolean canContinue = game.canPlayerContinueGame(player.getName());
            if (!canContinue) {
                OutputView.showIsBurst();
                break;
            }
            OutputView.showPlayerDeck(currentPlayer);
        }
    }
}
