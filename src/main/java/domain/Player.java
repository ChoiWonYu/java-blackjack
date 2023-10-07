package domain;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import java.util.List;

public class Player {

    protected final int MAX_NUMBER = 21;

    protected final Name name;
    protected final Deck deck;
    private BettingAmount bettingAmount;

    protected Player(Name name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    private Player(Name name, Deck deck, BettingAmount bettingAmount) {
        this.name = name;
        this.deck = deck;
        this.bettingAmount = bettingAmount;
    }


    public static Player createDefault(final String name, final int amount) {
        Deck initialDeck = Deck.createDefaultDeck();
        Name playersName = new Name(name);
        BettingAmount bettingAmount = new BettingAmount(amount);
        return new Player(playersName, initialDeck, bettingAmount);
    }

    public void addCardsToDeck(final List<Card> firstCards) {
        deck.addCards(firstCards);
    }

    public void addCardToDeck(final Card card) {
        deck.addCard(card);
    }

    public boolean isBlackJack() {
        int deckSum = deck.getSum();
        return deckSum == MAX_NUMBER;
    }

    public boolean isBurst() {
        int deckSum = deck.getSum();
        return deckSum > MAX_NUMBER;
    }

    public int getCardSum() {
        return deck.getSum();
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
        int scoreSum=getCardSum();
        PlayerDeck commonDto=toCommonDto();
        return PlayerDeckResult.of(commonDto, scoreSum);
    }

    public boolean hasSameNameValue(String playerName) {
        return name.isSameName(playerName);
    }
}
