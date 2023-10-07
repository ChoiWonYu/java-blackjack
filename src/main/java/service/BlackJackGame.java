package service;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import controller.dto.PlayerRevenue;
import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {

    private final int INITIAL_CARDS_COUNT = 2;

    private final Dealer dealer;
    private final Players players;
    private final Cards cards;
    private final RevenueCalculator calculator;

    private BlackJackGame(Dealer dealer, Players players, Cards cards,
        RevenueCalculator calculator) {
        this.dealer = dealer;
        this.players = players;
        this.cards = cards;
        this.calculator = calculator;
    }

    public static BlackJackGame createDefaultGame() {
        Dealer defaultDealer = Dealer.createDefaultDealer();
        Cards cards = Cards.createDefaultCards();
        Players players = Players.createInitialPlayers(new ArrayList<>());
        RevenueCalculator revenueCalculator = RevenueCalculator.createCalculatorWithoutPlayer(
            defaultDealer);
        return new BlackJackGame(defaultDealer, players, cards, revenueCalculator);
    }

    public void firstDealOutCards() {
        List<Card> dealerCards = pickCards(INITIAL_CARDS_COUNT);
        dealer.addCardsToDeck(dealerCards);

        players.actEachPlayer((player) -> {
            List<Card> pickedCards = pickCards(INITIAL_CARDS_COUNT);
            player.addCardsToDeck(pickedCards);
        });
    }

    public PlayerDeck giveCardToPlayer(String playerName) {
        Card pickedCard = pickCard();
        Player player = players.getPlayerByName(playerName);
        player.addCardToDeck(pickedCard);
        return player.toCommonDto();
    }

    public List<String> getPlayerNameValues() {
        return players.getNameValues();
    }

    private List<Card> pickCards(int cardCount) {
        return cards.getCardsByCardCount(cardCount);
    }

    private Card pickCard() {
        return cards.getCard();
    }

    public void addPlayerToGame(final Player player) {
        players.addPlayer(player);
    }

    public List<PlayerDeck> getAllPlayers() {
        return players.getPlayers();
    }

    public PlayerDeck getDealer() {
        return dealer.toCommonDto();
    }

    public boolean canPlayerContinueGame(final String name) {
        Player player = players.getPlayerByName(name);
        if (player.isBurst()) {
            return false;
        }

        return true;
    }

    public boolean haveToPickMoreCard() {
        return dealer.haveToPickMoreCard();
    }

    public PlayerDeck giveDealerMoreCard() {
        Card pickedCard = pickCard();
        dealer.addCardToDeck(pickedCard);
        return dealer.toCommonDto();
    }

    public PlayerDeckResult getDealerResult() {
        return dealer.toDtoIncludeResult();
    }

    public List<PlayerDeckResult> getAllPlayerResults() {
        return players.getPlayerResults();
    }

    public void calculateRevenue() {
        players.actEachPlayer(calculator::calculateRevenue);
    }

    public List<PlayerRevenue> getPlayersRevenue() {
        PlayerRevenue dealerRevenue=dealer.toRevenueDto();
        List<PlayerRevenue> playerRevenues=players.getPlayerRevenueResults();
        playerRevenues.add(0,dealerRevenue);
        return new ArrayList<>(playerRevenues);
    }
}
