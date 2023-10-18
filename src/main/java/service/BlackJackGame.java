package service;

import static controller.mapper.PlayerMapper.hideCardFromDto;
import static controller.mapper.PlayerMapper.playerToCommonDto;
import static controller.mapper.PlayerMapper.playerToResultDeck;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import controller.dto.PlayerRevenue;
import domain.GameResults;
import domain.card.Card;
import domain.card.Cards;
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

    private BlackJackGame(final Dealer dealer, final Players players, final Cards cards) {
        this.dealer = dealer;
        this.players = players;
        this.cards = cards;
    }

    public static BlackJackGame createDefaultGame() {
        Dealer defaultDealer = Dealer.createDefaultDealer();
        Cards cards = Cards.createDefaultCards();
        Players players = Players.createInitialPlayers(new ArrayList<>());
        return new BlackJackGame(defaultDealer, players, cards);
    }

    public void createGamePlayer(final String name, final int bettingAmount) {
        Player player = Player.createDefault(name, bettingAmount);
        players.addPlayer(player);
    }

    public void firstDealOutCards() {
        List<Card> dealerCards = cards.pickInitialCards(INITIAL_CARDS_COUNT);
        dealer.addCardsToDeck(dealerCards);

        players.actEachPlayer((player) -> {
            List<Card> pickedCards = cards.pickInitialCards(INITIAL_CARDS_COUNT);
            player.addCardsToDeck(pickedCards);
        });
    }

    public PlayerDeck giveCardToPlayer(final String playerName) {
        Card pickedCard = pickCard();
        Player player = players.getPlayerByName(playerName);
        player.addCardToDeck(pickedCard);
        return playerToCommonDto(player);
    }

    public List<String> getPlayerNameValues() {
        return players.getNameValues();
    }

    public List<PlayerDeck> getAllPlayers() {
        return players.getPlayers();
    }

    public PlayerDeck getDealer() {
        PlayerDeck commonDto = playerToCommonDto(dealer);
        hideCardFromDto(commonDto);
        return commonDto;
    }

    public boolean canPlayerContinueGame(final String name) {
        Player player = players.getPlayerByName(name);
        return !player.isBurst();
    }

    public boolean haveToPickMoreCard() {
        return dealer.haveToPickMoreCard();
    }

    public void giveDealerMoreCard() {
        Card pickedCard = pickCard();
        dealer.addCardToDeck(pickedCard);
    }

    public PlayerDeckResult getDealerResult() {
        return playerToResultDeck(dealer);
    }

    public List<PlayerDeckResult> getAllPlayerResults() {
        return players.getPlayerResults();
    }

    public List<PlayerRevenue> calculateRevenue() {
        RevenueCalculator calculator = RevenueCalculator.createCalculator(dealer, players);
        GameResults results = calculator.calculateRevenues();
        return results.getPlayerRevenues();
    }

    private Card pickCard() {
        return cards.getCard();
    }
}
