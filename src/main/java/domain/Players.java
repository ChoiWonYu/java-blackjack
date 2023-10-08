package domain;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import controller.dto.PlayerRevenue;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players createInitialPlayers(List<Player> initialPlayers) {
        return new Players(initialPlayers);
    }

    public void actEachPlayer(final Consumer<Player> act) {
        players.forEach(act);
    }

    public List<String> getNameValues() {
        return players.stream()
            .map(Player::getNameValue)
            .collect(Collectors.toList());
    }

    public void addPlayer(final Player player) {
        validateDuplicateName(player);
        players.add(player);
    }

    private void validateDuplicateName(final Player targetPlayer) {
        boolean isDuplicated = players.stream()
            .anyMatch(player -> player.hasSameName(targetPlayer));

        if(isDuplicated){
            throw new IllegalArgumentException();
        }
    }

    public List<PlayerDeck> getPlayers() {
        return players.stream()
            .map(Player::toCommonDto)
            .collect(Collectors.toList());
    }

    public Player getPlayerByName(final String playerName) {
        return players.stream()
            .filter(player -> player.hasSameNameValue(playerName))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException());
    }

    public List<PlayerDeckResult> getPlayerResults() {
        return players.stream()
            .map(Player::toDtoIncludeResult)
            .collect(Collectors.toList());
    }

    public List<PlayerRevenue> getPlayerRevenueResults() {
        return players.stream()
            .map(Player::toRevenueDto)
            .collect(Collectors.toList());
    }
}
