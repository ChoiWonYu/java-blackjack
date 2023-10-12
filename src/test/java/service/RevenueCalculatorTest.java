package service;

import static domain.card.Value.ACE;
import static domain.card.Value.KING;
import static domain.card.Value.ONE;
import static domain.card.Value.SEVEN;
import static domain.card.Value.TWO;
import static fixture.RevenueFixture.getPlayerRevenue;
import static fixture.RevenueFixture.giveTwoCardToPlayer;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Dealer;
import domain.GameResults;
import domain.Player;
import domain.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RevenueCalculatorTest {

    private final int PLAYER_BETTING_AMOUNT = 1000;
    private final int BLACKJACK_PLAYER_BETTING_AMOUNT = 1500;

    private Dealer dealer;
    private Player player;
    private Players players;
    private RevenueCalculator calculator;

    @BeforeEach
    void init() {
        dealer = Dealer.createDefaultDealer();
        player = Player.createDefault("test", 1000);
        players = Players.createInitialPlayers(List.of(player));
        calculator = RevenueCalculator.createCalculator(dealer, players);
    }

    @Test
    @DisplayName("플레이어가 블랙잭일 때, 플레이어는 배팅금의 1.5배를 얻고, 딜러는 그만큼 잃는다.")
    void when_only_player_is_black_jack() {
        // given
        giveTwoCardToPlayer(ACE, KING, player);
        giveTwoCardToPlayer(ONE, SEVEN, dealer);

        // when
        GameResults results = calculator.calculateRevenues();
        double playerRevenue = getPlayerRevenue(results, player);
        double dealerRevenue = getPlayerRevenue(results, dealer);

        // then
        assertAll(() -> {
            assertEquals(playerRevenue, BLACKJACK_PLAYER_BETTING_AMOUNT);
            assertEquals(dealerRevenue, -BLACKJACK_PLAYER_BETTING_AMOUNT);
        });
    }

    @Test
    @DisplayName("딜러가 블랙잭이면, 플레이어는 배팅금을 잃는고, 딜러는 그만큼 얻는다.")
    void when_only_dealer_is_black_jack() {
        // given
        giveTwoCardToPlayer(ONE, SEVEN, player);
        giveTwoCardToPlayer(ACE, KING, dealer);

        // when
        GameResults results = calculator.calculateRevenues();
        double playerRevenue = getPlayerRevenue(results, player);
        double dealerRevenue = getPlayerRevenue(results, dealer);

        // then
        assertAll(() -> {
            assertEquals(playerRevenue, -PLAYER_BETTING_AMOUNT);
            assertEquals(dealerRevenue, PLAYER_BETTING_AMOUNT);
        });
    }

    @Test
    @DisplayName("둘 다 블랙잭이면, 돈을 그냥 돌려받고 둘 다 수익이 0이다.")
    void when_both_black_jack() {
        // given
        giveTwoCardToPlayer(ACE, KING, player);
        giveTwoCardToPlayer(ACE, KING, dealer);

        // when
        GameResults results = calculator.calculateRevenues();
        double playerRevenue = getPlayerRevenue(results, player);
        double dealerRevenue = getPlayerRevenue(results, dealer);

        // then
        assertAll(() -> {
            assertEquals(playerRevenue, 0);
            assertEquals(dealerRevenue, 0);
        });
    }

    @Test
    @DisplayName("플레이어의 숫자가 더 높으면, 배팅금을 얻고 딜러는 그만큼 잃는다.")
    void when_player_number_sum_bigger_than_dealer() {
        // given
        giveTwoCardToPlayer(ONE, TWO, dealer);
        giveTwoCardToPlayer(ONE, SEVEN, player);

        // when
        GameResults results = calculator.calculateRevenues();
        double playerRevenue = getPlayerRevenue(results, player);
        double dealerRevenue = getPlayerRevenue(results, dealer);

        // then
        assertAll(() -> {
            assertEquals(playerRevenue, PLAYER_BETTING_AMOUNT);
            assertEquals(dealerRevenue, -PLAYER_BETTING_AMOUNT);
        });
    }

    @Test
    @DisplayName("딜러의 숫자가 더 높거나 같으면, 플레이어는 배팅금을 잃고, 딜러는 그만큼 얻는다.")
    void when_dealer_number_sum_bigger_than_player() {
        // given
        giveTwoCardToPlayer(ONE, SEVEN, dealer);
        giveTwoCardToPlayer(ONE, TWO, player);

        // when
        GameResults results = calculator.calculateRevenues();
        double playerRevenue = getPlayerRevenue(results, player);
        double dealerRevenue = getPlayerRevenue(results, dealer);

        // then
        assertAll(() -> {
            assertEquals(playerRevenue, -PLAYER_BETTING_AMOUNT);
            assertEquals(dealerRevenue, PLAYER_BETTING_AMOUNT);
        });
    }

    @Test
    @DisplayName("딜러가 burst면, 플레이어는 배팅금을 얻고, 딜러는 그만큼 잃는다.")
    void when_dealer_is_burst_player() {
        // given
        giveTwoCardToPlayer(KING, KING, dealer);
        giveTwoCardToPlayer(KING, KING, dealer);
        giveTwoCardToPlayer(ONE, TWO, player);

        // when
        GameResults results = calculator.calculateRevenues();
        double playerRevenue = getPlayerRevenue(results, player);
        double dealerRevenue = getPlayerRevenue(results, dealer);

        // then
        assertAll(() -> {
            assertEquals(playerRevenue, PLAYER_BETTING_AMOUNT);
            assertEquals(dealerRevenue, -PLAYER_BETTING_AMOUNT);
        });
    }

    @Test
    @DisplayName("플레이어가 burst면, 플레이어는 돈을 잃고, 딜러는 그만큼 얻는다.")
    void when_player_is_burst_dealer() {
        // given
        giveTwoCardToPlayer(KING, KING, player);
        giveTwoCardToPlayer(KING, KING, player);
        giveTwoCardToPlayer(ONE, TWO, dealer);

        // when
        GameResults results = calculator.calculateRevenues();
        double playerRevenue = getPlayerRevenue(results, player);
        double dealerRevenue = getPlayerRevenue(results, dealer);

        // then
        assertAll(() -> {
            assertEquals(playerRevenue, -PLAYER_BETTING_AMOUNT);
            assertEquals(dealerRevenue, PLAYER_BETTING_AMOUNT);
        });
    }
}
