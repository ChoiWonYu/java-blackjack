package view;

import controller.dto.PlayerDeck;

public class OutputView {

    public static void askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void askPlayerRevenues(String name) {
        System.out.printf("%s의 배팅 금액은?\n",name);
    }

    public static void noticeFirstDealOut(String names) {
        System.out.printf("딜러와 %s에게 2장씩 나누었습니다.\n", names);
    }

    public static void showPlayerDeck(final PlayerDeck commonDto) {
        System.out.printf("%s: %s\n",commonDto.getName(),commonDto.getCards());
    }
}
