package view;

import controller.dto.PlayerDeck;
import controller.dto.PlayerDeckResult;
import controller.dto.PlayerRevenue;

public class OutputView {

    public static void askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void askPlayerRevenues(String name) {
        newLine();
        System.out.printf("%s의 배팅 금액은?\n", name);
    }

    public static void noticeFirstDealOut(String names) {
        newLine();
        System.out.printf("딜러와 %s에게 2장씩 나누었습니다.\n", names);
    }

    public static void showPlayerDeck(final PlayerDeck commonDto) {
        System.out.printf("%s카드: %s\n", commonDto.getName(), commonDto.getCards());
    }

    public static void showDealerDeck(final PlayerDeck commonDto) {
        System.out.printf("%s: %s\n", commonDto.getName(), commonDto.getCards());
    }

    public static void askMoreCards(final PlayerDeck commonDto) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", commonDto.getName());
    }

    public static void showIsBurst() {
        System.out.println("Burst!");
    }

    public static void noticeDealerPickedCard() {
        System.out.println("딜러는 16장 이하라 한장의 카드를 더 받았습니다.");
    }

    public static void showDeckWithResult(final PlayerDeckResult result) {
        System.out.printf("%s: %s - 결과: %d\n", result.getName(), result.getCards(),
            result.getResult());
    }

    public static void showRevenues(final PlayerRevenue revenue) {
        System.out.printf("%s: %.0f\n", revenue.getName(), revenue.getRevenue());
    }

    public static void newLine() {
        System.out.println();
    }

    public static void noticeRevenueDescription() {
        System.out.println("## 최종 수익");
    }
}
