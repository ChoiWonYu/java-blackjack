package controller;

import controller.dto.PlayerDeck;
import domain.Player;
import java.util.Arrays;
import java.util.List;
import service.BlackJackGame;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    private final InputView reader;
    private final BlackJackGame game;

    private BlackJackGameController(InputView reader, BlackJackGame game) {
        this.reader = reader;
        this.game = game;
    }

    public static BlackJackGameController createDefaultGameController(InputView reader) {
        BlackJackGame game = BlackJackGame.createDefaultGame();
        return new BlackJackGameController(reader, game);
    }

    public void startGame() {
        String[] names = getPlayerNames();
        getPlayerBettingAmount(names);

        game.firstDealOutCards();
        List<String> playerNames = game.getPlayerNameValues();
        OutputView.noticeFirstDealOut(String.join(",", playerNames));

        PlayerDeck dealerInfo = game.getDealer();
        OutputView.showPlayerDeck(dealerInfo);

        List<PlayerDeck> allPlayers = game.getAllPlayers();
        allPlayers.forEach(OutputView::showPlayerDeck);

    }

    private String[] getPlayerNames() {
        OutputView.askPlayerNames();
        String inputNames = reader.getInputLine();
        return inputNames.split(",");
    }

    private void getPlayerBettingAmount(String[] names) {
        Arrays.stream(names).forEach(name -> {
            OutputView.askPlayerRevenues(name);
            int bettingAmount = reader.getInteger();
            addPlayerToGame(name, bettingAmount);
        });
    }

    private void addPlayerToGame(final String name, final int bettingAmount) {
        Player player = Player.createDefault(name, bettingAmount);
        game.addPlayerToGame(player);
    }

}
