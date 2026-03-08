import java.util.Scanner;
import Game.Game;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Would you like to start a new game? y/n");
            String startCheck = scanner.next();
            if (startCheck.toLowerCase().equals("y")) {
                Game game = new Game();
                game.startGame();

                while (!game.isCheckmate()) {
                    game.executeTurn();
                }
                System.out.println(game.getCurrentTurn() + " Has been checkmated. Game over");
                continue;

            } else {
                System.out.println("Goodbye!");
                break;
            }

        }

    }

}