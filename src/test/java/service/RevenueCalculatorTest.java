package service;

import static domain.card.Shape.CLOVER;
import static domain.card.Value.ACE;
import static domain.card.Value.KING;
import static domain.card.Value.ONE;
import static domain.card.Value.SEVEN;
import static domain.card.Value.TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.Dealer;
import domain.Player;
import domain.card.Card;
import domain.card.Value;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RevenueCalculatorTest {

    private final int PLAYER_BETTING_AMOUNT=1000;
    private final int BLACKJACK_PLAYER_BETTING_AMOUNT=1500;

    private Dealer dealer;
    private Player player;
    private RevenueCalculator calculator;

    @BeforeEach
    void init() {
        dealer=Dealer.createDefaultDealer();
        player=Player.createDefault("test",1000);
        calculator=RevenueCalculator.createCalculatorWithoutPlayer(dealer);
    }

    @Test
    @DisplayName("플레이어가 블랙잭일 때, 플레이어는 배팅금의 1.5배를 얻고, 딜러는 그만큼 잃는다.")
    void when_only_player_is_black_jack() {
        // given
        givePlayerCards(ACE,KING);
        giveDealerCards(ONE,SEVEN);

        // when
        calculator.calculateRevenue(player);

        // then
        assertEquals(getPlayerRevenue(player),BLACKJACK_PLAYER_BETTING_AMOUNT);
        assertEquals(getPlayerRevenue(dealer),-BLACKJACK_PLAYER_BETTING_AMOUNT);
    }

    @Test
    @DisplayName("딜러가 블랙잭이면, 플레이어는 배팅금을 잃는고, 딜러는 그만큼 얻는다.")
    void when_only_dealer_is_black_jack() {
        // given
        givePlayerCards(ONE, SEVEN);
        giveDealerCards(ACE, KING);

        // when
        calculator.calculateRevenue(player);

        // then
        assertEquals(getPlayerRevenue(player),-PLAYER_BETTING_AMOUNT);
        assertEquals(getPlayerRevenue(dealer),PLAYER_BETTING_AMOUNT);
    }

    @Test
    @DisplayName("둘 다 블랙잭이면, 돈을 그냥 돌려받고 둘 다 수익이 0이다.")
    void when_both_black_jack() {
        // given
        giveDealerCards(ACE, KING);
        givePlayerCards(ACE, KING);

        // when
        calculator.calculateRevenue(player);

        // then
        assertEquals(getPlayerRevenue(player),0);
        assertEquals(getPlayerRevenue(dealer),0);
    }

    @Test
    @DisplayName("플레이어의 숫자가 더 높으면, 배팅금을 얻고 딜러는 그만큼 잃는다.")
    void when_player_number_sum_bigger_than_dealer() {
        // given
        giveDealerCards(ONE, TWO);
        givePlayerCards(ONE, SEVEN);

        // when
        calculator.calculateRevenue(player);
        // then
        assertPlayerEarnBettingAmount();
    }

    @Test
    @DisplayName("딜러의 숫자가 더 높거나 같으면, 플레이어는 배팅금을 잃고, 딜러는 그만큼 얻는다.")
    void when_dealer_number_sum_bigger_than_player() {
        // given
        giveDealerCards(ONE, SEVEN);
        givePlayerCards(ONE, TWO);

        // when
        calculator.calculateRevenue(player);

        // then
        assertDealerEarnBettingAmount();
    }

    @Test
    @DisplayName("딜러가 burst면, 플레이어는 배팅금을 얻고, 딜러는 그만큼 잃는다.")
    void when_dealer_is_burst_player() {
        // given
        giveDealerCards(KING, KING);
        giveDealerCards(KING, KING);
        givePlayerCards(ONE, TWO);

        // when
        calculator.calculateRevenue(player);

        // then
        assertPlayerEarnBettingAmount();
    }

    @Test
    @DisplayName("플레이어가 burst면, 플레이어는 돈을 잃고, 딜러는 그만큼 얻는다.")
    void when_player_is_burst_dealer() {
        // given
        givePlayerCards(KING, KING);
        givePlayerCards(KING, KING);
        giveDealerCards(ONE, TWO);

        // when
        calculator.calculateRevenue(player);

        // then
        assertDealerEarnBettingAmount();
    }

    private void assertPlayerEarnBettingAmount() {
        assertEquals(getPlayerRevenue(player),PLAYER_BETTING_AMOUNT);
        assertEquals(getPlayerRevenue(dealer),-PLAYER_BETTING_AMOUNT);
    }

    private void assertDealerEarnBettingAmount() {
        assertEquals(getPlayerRevenue(player),-PLAYER_BETTING_AMOUNT);
        assertEquals(getPlayerRevenue(dealer),PLAYER_BETTING_AMOUNT);
    }

    private List<Card> pickTwoCards(Value firstValue, Value secondValue) {
        Card firstCard = new Card(firstValue, CLOVER);
        Card secondCard = new Card(secondValue, CLOVER);

        return List.of(firstCard, secondCard);
    }

    private void givePlayerCards(Value firstValue, Value secondValue) {
        List<Card> playerCards = pickTwoCards(firstValue, secondValue);
        player.addCardsToDeck(playerCards);
    }

    private void giveDealerCards(Value firstValue, Value secondValue) {
        List<Card> dealerCards = pickTwoCards(firstValue, secondValue);
        dealer.addCardsToDeck(dealerCards);
    }

    private double getPlayerRevenue(Player player) {
        return player.toRevenueDto().getRevenue();
    }
}
