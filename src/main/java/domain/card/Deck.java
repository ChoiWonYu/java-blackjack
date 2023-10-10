package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    protected final int MAX_NUMBER = 21;

    private final List<Card> deck;

    private Deck(final List<Card> deck) {
        this.deck = deck;
    }

    public static Deck createDefaultDeck() {
        return new Deck(new ArrayList<>());
    }

    public void addCard(final Card card) {
        deck.add(card);
    }

    public void addCards(final List<Card> cards) {
        deck.addAll(cards);
    }

    public List<String> getCardInfo() {
        return deck.stream()
            .map(card ->
                card.getDisplay() + card.getShape()
            )
            .collect(Collectors.toList());
    }

    public boolean isBlackJack() {
        int deckSum = getBestSum();
        return deckSum == MAX_NUMBER;
    }

    public boolean isBurst() {
        int deckSum = getBestSum();
        return deckSum > MAX_NUMBER;
    }

    public boolean hasAce() {
        return deck.stream()
            .anyMatch(Card::isAceCard);
    }

    public void changeAceValue() {
        deck.stream()
            .filter(Card::isAceCard)
            .forEach(Card::changeAceValue);
    }

    public void findBetterDeck() {
        int prevSum = getSum();
        changeAceValue();
        int currentSum = getSum();

        if (isPrevSumBetter(prevSum, currentSum) || currentSum > MAX_NUMBER) {
            changeAceValue();
        }
    }

    public int getBestSum() {
        if (hasAce()) {
            findBetterDeck();
        }
        return getSum();
    }

    private int getSum() {
        return deck.stream()
            .mapToInt(Card::getNumber)
            .sum();
    }

    private boolean isPrevSumBetter(int prevSum, int currentSum) {
        return currentSum > prevSum && prevSum <= MAX_NUMBER;
    }
}
