import controller.BlackJackGameController;
import java.util.Scanner;
import view.InputView;

public class BlackJackMain {

    public static void main(String[] args) {
        InputView inputView = new InputView(new Scanner(System.in));
        BlackJackGameController gameController = BlackJackGameController.createDefaultGameController(
            inputView);

        gameController.startGame();
    }

}
