package domain;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import controller.mapper.PlayerMapper;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
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

    public List<Name> getNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    public void addPlayer(final Player player) {
        validateDuplicateName(player);
        players.add(player);
    }

    private void validateDuplicateName(final Player targetPlayer) {
        boolean isDuplicated = players.stream()
            .anyMatch(player -> player.hasSameNameValue(targetPlayer.getNameValue()));

        if (isDuplicated) {
            throw new IllegalArgumentException();
        }
    }

    public List<PlayerDeck> getPlayers() {
        return players.stream()
            .map(PlayerMapper::playerToCommonDto)
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
            .map(PlayerMapper::playerToResultDeck)
            .collect(Collectors.toList());
    }
}
