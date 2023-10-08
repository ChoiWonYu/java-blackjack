package domain;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import controller.dto.PlayerRevenue;
import domain.card.Card;
import domain.card.Deck;
import java.util.List;

public class Player {

    protected final Name name;
    protected final Deck deck;
    protected final Revenue revenue;
    private BettingAmount bettingAmount;

    protected Player(Name name, Deck deck, Revenue revenue) {
        this.name = name;
        this.deck = deck;
        this.revenue = revenue;
    }

    private Player(Name name, Deck deck, BettingAmount bettingAmount, Revenue revenue) {
        this.name = name;
        this.deck = deck;
        this.bettingAmount = bettingAmount;
        this.revenue = revenue;
    }


    public static Player createDefault(final String name, final int amount) {
        Deck initialDeck = Deck.createDefaultDeck();
        Name playersName = new Name(name);
        BettingAmount bettingAmount = new BettingAmount(amount);
        Revenue initialRevenue = Revenue.createDefaultRevenue();
        return new Player(playersName, initialDeck, bettingAmount, initialRevenue);
    }

    public void addCardsToDeck(final List<Card> firstCards) {
        deck.addCards(firstCards);
    }

    public void addCardToDeck(final Card card) {
        deck.addCard(card);
    }

    public boolean isBlackJack() {
        return deck.isBlackJack();
    }

    public boolean isBurst() {
        return deck.isBurst();
    }

    public int getCardSum() {
        return deck.getBestSum();
    }

    public String getNameValue() {
        return name.getName();
    }

    public List<String> getCardInfos() {
        return deck.getCardInfo();
    }

    public PlayerDeck toCommonDto() {
        List<String> cardInfos = getCardInfos();
        return new PlayerDeck(getNameValue(), String.join(", ", cardInfos));
    }

    public PlayerDeckResult toDtoIncludeResult() {
        int scoreSum = getCardSum();
        PlayerDeck commonDto = toCommonDto();
        return PlayerDeckResult.of(commonDto, scoreSum);
    }

    public boolean hasSameNameValue(String playerName) {
        return name.isSameName(playerName);
    }

    public void lostAmount(double amount) {
        revenue.lostAmount(amount);
    }

    public void winAmount(double amount) {
        revenue.winAmount(amount);
    }

    public int getBettingAmount() {
        return bettingAmount.getBettingAmount();
    }

    public boolean hasBiggerSum(final int cardSum) {
        int myCardSum = getCardSum();
        return myCardSum > cardSum;
    }

    public PlayerRevenue toRevenueDto() {
        return new PlayerRevenue(getNameValue(), revenue.getRevenue());
    }
}
