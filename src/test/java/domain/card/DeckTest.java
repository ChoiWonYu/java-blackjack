package domain.card;

import static domain.card.Shape.HEART;
import static domain.card.Value.ACE;
import static domain.card.Value.KING;
import static domain.card.Value.QUEEN;
import static domain.card.Value.SEVEN;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fixture.CardsFixture;
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
        List<Card> cards = CardsFixture.pickCards(ACE, KING);
        deck.addCards(cards);

        // when & then
        assertTrue(deck.isBlackJack());
    }

    @Test
    @DisplayName("카드의 합이 21을 넘어가면 Burst를 알려준다.")
    void when_card_sum_over_21_notice_burst() {
        // given
        List<Card> cards = CardsFixture.pickCards(QUEEN, KING);
        deck.addCards(cards);
        addCardToDeck(SEVEN);

        // when & then
        assertTrue(deck.isBurst());
    }

    @Test
    @DisplayName("에이스가 있을 때, 에이스가 있다고 알려준다.")
    void when_contains_ace_notice_has_ace() {
        // given
        List<Card> cards = CardsFixture.pickCards(ACE, KING);
        deck.addCards(cards);

        // when & then
        assertTrue(deck.hasAce());
    }

    @Test
    @DisplayName("에이스가 있을 때, 11 값을 가졌을 때 Burst라면 값을 1로 바꾼다.")
    void when_contains_ace_and_is_burst_change_primary_value_to_1() {
        // given
        List<Card> cards = CardsFixture.pickCards(ACE, KING);
        deck.addCards(cards);
        addCardToDeck(SEVEN);

        // when & then
        assertSoftly((softly) -> {
            softly.assertThat(deck.isBurst()).isFalse();
            softly.assertThat(ACE.getPrimaryValue()).isEqualTo(1);
            softly.assertThat(deck.getBestSum()).isEqualTo(1 + 10 + 7);
        });
    }

    private void addCardToDeck(Value value) {
        Card card = new Card(value, HEART);
        deck.addCard(card);
    }
}
