package domain;

import controller.dto.PlayerDeck;
import java.util.List;
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

    public int getPlayersCount() {
        return players.size();
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
        players.add(player);
    }

    public List<PlayerDeck> getPlayers() {
        return players.stream()
            .map(Player::toCommonDto)
            .collect(Collectors.toList());
    }
}
