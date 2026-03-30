import java.io.File;
import java.util.Scanner;
import Game.Game;
import Game.Replay;
import util.FileManager;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("At any time type \"exit\" to quit");

        while (true) {

            String startCheck = playOrReview();

            if (startCheck.equals("1")) {
                playGame();
                continue;
            }
            if (startCheck.equals("2")) {
                replayGame();
                continue;
            }
            if (startCheck.equals("3")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("You must enter a valid input");
            }

        }

    }

    private static void replayGame() {

        File savesFolder = new File("../saves");
        String[] saves = savesFolder.list();

        if (saves.length == 0) {
            System.out.println("You have no saves. You must add one to the saves folder");

        } else {
            System.out.println("---------------");
            for (int i = 0; i < saves.length; i++) {
                System.out.println(i + 1 + ": " + saves[i]);
            }
            System.out.println("Enter the number of the save you want to replay: ");
            String saveString = scanner.next();

            if (saveString.toLowerCase().equals("exit")) {
                System.exit(0);
            }

            int saveNumber = Integer.parseInt(saveString);

            if (saveNumber > (saves.length + 1) || saveNumber < 1) {
                System.out.println("That is an invalid selection");
                replayGame();

            } else {
                Replay replay = new Replay(saves[saveNumber - 1]);
                for (int i = 0; i < saves.length; i++) {
                    if (saveNumber == i + 1) {
                        replay.replayGame();
                    }
                }

            }
        }

    }

    private static void playGame() {

        Game game = null;
        System.out.println("Enter 1 to start a new game or 2 to load your own game");
        String inputString = scanner.next();

        if (inputString.toLowerCase().equals("exit")) {
            System.out.println("Goodbye!");
            System.exit(0);
        }

        int input = Integer.parseInt(inputString);

        if (input == 1) {

            game = new Game();
            game.startGame();

        } else {

            System.out.println("What is the name of the save? do not include .txt");
            String fileName = scanner.nextLine();

            if (fileName.toLowerCase().equals("exit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            File file = new File(fileName + ".txt");
            game = FileManager.loadGame(file);
        }

        if (game != null) {
            while (!game.isCheckmate()) {
                game.executeTurn();
            }
            System.out.println(game.getCurrentTurn() + " Has been checkmated. Game over");
        }

    }

    private static String playOrReview() {
        System.out.println("Would you like to play or review a game");
        System.out.println("Play game: enter 1");
        System.out.println("Review game: enter 2");
        System.out.println("Quit program: 3");
        return scanner.nextLine();

    }

}