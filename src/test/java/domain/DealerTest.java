package domain;

import static org.junit.jupiter.api.Assertions.assertFalse;

import domain.card.Card;
import domain.card.Value;
import fixture.CardsFixture;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void init() {
        this.dealer = Dealer.createDefaultDealer();
    }

    @Test
    @DisplayName("17이상이면 카드를 뽑을 수 없다고 알려준다.")
    public void when_sum_over_17_have_to_pick_more_card() {
        // given
        List<Card> cards = CardsFixture.pickCards(Value.SEVEN, Value.KING);

        // when
        dealer.addCardsToDeck(cards);

        //then
        assertFalse(dealer.haveToPickMoreCard());
    }
}
