package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void init() {
        this.dealer = Dealer.createDefaultDealer("test dealer");
    }

    @Test
    @DisplayName("17이상이면 카드를 또 뽑아야 한다고 알려준다.")
    public void when_sum_over_17_have_to_pick_more_card() {
        // given
        List<Card> cards = firstPickCards(7, 10);

        // when
        dealer.addCardToDeck(cards);

        //then
        assertTrue(dealer.haveToPickMoreCard());
    }

    private List<Card> firstPickCards(int firstNum, int secondNum) {
        Card firstCard = new Card(firstNum, "클로버");
        Card secondCard = new Card(secondNum, "클로버");

        return List.of(firstCard, secondCard);
    }
}
