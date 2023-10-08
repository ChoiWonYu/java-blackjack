package domain.card;

import static domain.card.Shape.CLOVER;
import static domain.card.Shape.HEART;
import static domain.card.Value.ACE;
import static domain.card.Value.KING;
import static domain.card.Value.QUEEN;
import static domain.card.Value.SEVEN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private Deck deck;

    @BeforeEach
    void init() {
        deck = Deck.createDefaultDeck();
    }

    @Test
    @DisplayName("카드의 합이 21이면, 블랙잭이라고 알려준다.")
    void when_card_sum_equals_21_notice_black_jack() {
        // given
        pickTwoCard(ACE, KING);

        // when & then
        assertTrue(deck.isBlackJack());
    }

    @Test
    @DisplayName("카드의 합이 21을 넘어가면 Burst를 알려준다.")
    void when_card_sum_over_21_notice_burst() {
        // given
        pickTwoCard(QUEEN, KING);
        addCardToDeck(SEVEN);

        // when & then
        assertTrue(deck.isBurst());
    }

    @Test
    @DisplayName("에이스가 있을 때, 에이스가 있다고 알려준다.")
    void when_contains_ace_notice_has_ace() {
        // given
        pickTwoCard(ACE, KING);

        // when & then
        assertTrue(deck.hasAce());
    }

    @Test
    @DisplayName("에이스가 있을 때, 11 값을 가졌을 때 Burst라면 값을 1로 바꾼다.")
    void when_contains_ace_and_is_burst_change_primary_value_to_1() {
        // given
        pickTwoCard(ACE, KING);
        addCardToDeck(SEVEN);

        // when & then
        assertFalse(deck.isBurst());
        assertEquals(ACE.getPrimaryValue(),1);
        assertEquals(deck.getBestSum(),1+10+7);
    }

    private void pickTwoCard(Value firstValue, Value secondValue) {
        Card firstCard = new Card(firstValue, CLOVER);
        Card secondCard = new Card(secondValue, CLOVER);

        deck.addCards(List.of(firstCard, secondCard));
    }

    private void addCardToDeck(Value value) {
        Card card = new Card(value, HEART);
        deck.addCard(card);
    }
}
