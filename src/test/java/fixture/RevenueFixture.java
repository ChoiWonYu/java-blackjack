package fixture;

import domain.GameResult;
import domain.GameResults;
import domain.Player;
import domain.card.Card;
import domain.card.Value;
import java.util.List;

public class RevenueFixture {

    public static double getPlayerRevenue(GameResults results, Player player) {
        GameResult result = results.findByName(player.getName());
        return result.getRevenueValue();
    }

    public static void giveTwoCardToPlayer(Value firstValue, Value secondValue, Player player) {
        List<Card> cards = CardsFixture.pickCards(firstValue, secondValue);
        player.addCardsToDeck(cards);
    }
}
