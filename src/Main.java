import java.io.File;
import java.util.Scanner;
import Game.Game;
import util.FileManager;

// The entire point of the giant overhaul is to make this very empty

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Game game = null;

        while (true) {
            System.out.println("Would you like to start a game? y/n");
            String startCheck = scanner.next();
            if (startCheck.toLowerCase().equals("y")) {

                System.out.println("Enter 1 to start a new game or 2 to load your own game");
                int input = scanner.nextInt();
                scanner.nextLine();

                if (input == 1) {

                    game = new Game();
                    game.startGame();

                } else {

                    System.out.println("What is the name of the save? do not include .txt");
                    String fileName = scanner.nextLine();
                    File file = new File(fileName + ".txt");
                    game = FileManager.loadGame(file);
                }

                if (game != null) {
                    while (!game.isCheckmate()) {
                        game.executeTurn();
                    }
                    System.out.println(game.getCurrentTurn() + " Has been checkmated. Game over");
                    continue;
                }

            } else {
                System.out.println("Goodbye!");
                break;
            }

        }

    }

}