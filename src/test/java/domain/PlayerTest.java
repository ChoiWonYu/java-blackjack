package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void init() {
        player = Player.createDefault("name");
    }

    @Test
    @DisplayName("첫 두 장의 카드가 21이면 블랙잭")
    public void when_first_two_cards_sum_21_blackjack() {
        // given
        List<Card> cards=firstPickCards(10,11);

        // when
        player.PickCards(cards);

        // then
        assertTrue(player.isBlackJack());
    }

    @Test
    @DisplayName("카드의 총합이 21을 넘으면 Burst")
    public void when_card_sum_over_21_burst() {
        // given
        List<Card> cards = firstPickCards(12, 11);

        // when
        player.PickCards(cards);

        // then
        assertTrue(player.isBurst());
    }

    @Test
    @DisplayName("주어진 카드의 총합을 결과로 반환한다.")
    public void return_given_cards_sum_as_result() {
        // given
        List<Card> cards = firstPickCards(12, 11);

        // when
        player.PickCards(cards);

        // then
        assertEquals(player.getResult(), 12+11);
    }

    private List<Card> firstPickCards(int firstNum, int secondNum) {
        Card firstCard = new Card(firstNum, "클로버");
        Card secondCard = new Card(secondNum, "클로버");

        return List.of(firstCard, secondCard);
    }

}
