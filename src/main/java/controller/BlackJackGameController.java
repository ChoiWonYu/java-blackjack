package controller;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import controller.dto.PlayerRevenue;
import domain.Player;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
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

        PlayerDeck dealerDeck = game.getDealer();
        OutputView.showDealerDeck(dealerDeck);

        List<PlayerDeck> allPlayers = game.getAllPlayers();
        actEachPlayerDto(allPlayers,OutputView::showPlayerDeck);
        OutputView.newLine();

        actEachPlayerDto(allPlayers,this::receiveCards);
        OutputView.newLine();

        checkDealerDeck();
        OutputView.newLine();

        game.calculateRevenue();
        showResult();
        OutputView.newLine();

        OutputView.noticeRevenueDescription();
        List<PlayerRevenue> revenues=game.getPlayersRevenue();
        revenues.forEach(OutputView::showRevenues);
    }

    private void showResult() {
        PlayerDeckResult dealerResult=game.getDealerResult();
        List<PlayerDeckResult> playerResults=game.getAllPlayerResults();

        playerResults.add(0, dealerResult);
        playerResults.forEach(OutputView::showDeckWithResult);
    }

    private void checkDealerDeck() {
        boolean needMoreCard = game.haveToPickMoreCard();
        if(!needMoreCard){
            return;
        }
        OutputView.noticeDealerPickedCard();
        game.giveDealerMoreCard();
    }

    private void actEachPlayerDto(final List<PlayerDeck> allPlayers,final Consumer<PlayerDeck> act) {
        allPlayers.forEach(act);
    }

    private void receiveCards(final PlayerDeck player) {
        PlayerDeck currentPlayer=player;
        while (true) {
            OutputView.askMoreCards(player);
            String answer=reader.getInputLine();
            boolean isYes = validateAnswer(answer);
            if (!isYes) {
                OutputView.showPlayerDeck(currentPlayer);
                break;
            }
            currentPlayer=game.giveCardToPlayer(player.getName());
            boolean canContinue=game.canPlayerContinueGame(player.getName());
            if (!canContinue) {
                OutputView.showIsBurst();
                break;
            }
            OutputView.showPlayerDeck(currentPlayer);
        }
    }

    private boolean validateAnswer(final String answer) {
        if(answer.equals("y")){
            return true;
        }
        if(answer.equals("n")){
            return false;
        }
        throw new IllegalArgumentException();
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
