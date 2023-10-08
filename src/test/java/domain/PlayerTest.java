package domain;

import static domain.card.Shape.CLOVER;
import static domain.card.Value.ACE;
import static domain.card.Value.JACK;
import static domain.card.Value.KING;
import static domain.card.Value.QUEEN;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.card.Card;
import domain.card.Value;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void init() {
        player = Player.createDefault("name", 1000);
    }

    @Test
    @DisplayName("첫 두 장의 카드가 21이면 블랙잭")
    public void when_first_two_cards_sum_21_blackjack() {
        // given
        List<Card> cards = firstPickCards(KING, ACE);

        // when
        player.addCardsToDeck(cards);

        // then
        assertTrue(player.isBlackJack());
    }

    @Test
    @DisplayName("카드의 총합이 21을 넘으면 Burst")
    public void when_card_sum_over_21_burst() {
        // given
        List<Card> cards = firstPickCards(KING, QUEEN);
        Card newCard = new Card(JACK, CLOVER);

        // when
        player.addCardsToDeck(cards);
        player.addCardToDeck(newCard);
        // then
        assertTrue(player.isBurst());
    }

    @Test
    @DisplayName("0보다 같거나 작은 amount로 플레이어를 생성하면 에러")
    public void when_create_player_with_less_then_zero_amount_throw_exception() {
        // given
        int amount=0;

        // when & then
        assertThrows(IllegalArgumentException.class, () ->
            Player.createDefault("test", amount));
    }

    private List<Card> firstPickCards(Value firstValue, Value secondValue) {
        Card firstCard = new Card(firstValue, CLOVER);
        Card secondCard = new Card(secondValue, CLOVER);

        return List.of(firstCard, secondCard);
    }
}
