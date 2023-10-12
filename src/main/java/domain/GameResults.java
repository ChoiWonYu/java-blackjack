package domain;

import controller.dto.PlayerRevenue;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameResults {

    private final List<GameResult> gameResults;

    private GameResults(final List<GameResult> gameResults) {
        this.gameResults=gameResults;
    }

    public static GameResults createDefault() {
        return new GameResults(new ArrayList<>());
    }

    public void addGameResult(GameResult result) {
        gameResults.add(result);
    }

    public GameResult findByName(Name name) {
        return gameResults.stream()
            .filter(result->result.hasSameName(name))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    public List<PlayerRevenue> getPlayerRevenues() {
        return gameResults.stream()
            .map(GameResult::toDto)
            .collect(Collectors.toList());
    }
}
