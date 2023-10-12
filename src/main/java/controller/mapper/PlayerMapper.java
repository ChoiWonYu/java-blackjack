package controller.mapper;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import domain.Player;
import java.util.List;

public class PlayerMapper {

    private static final int UNIQUE_SHOWN_CARD_INDEX = 0;
    private static final String PLAYER_DTO_CARD_DIVIDER = ", ";

    public static PlayerDeck playerToCommonDto(Player player) {
        List<String> cardInfos = player.getCardInfos();
        return new PlayerDeck(player.getNameValue(),
            String.join(PLAYER_DTO_CARD_DIVIDER, cardInfos));
    }

    public static void hideCardFromDto(PlayerDeck commonDto) {
        String cardsDto = commonDto.getCards();
        String[] cards = cardsDto.split(PLAYER_DTO_CARD_DIVIDER);
        commonDto.setCards(cards[UNIQUE_SHOWN_CARD_INDEX]);
    }

    public static PlayerDeckResult playerToResultDeck(Player player) {
        int scoreSum = player.getCardSum();
        PlayerDeck commonDto = playerToCommonDto(player);
        return PlayerDeckResult.of(commonDto, scoreSum);
    }
}
