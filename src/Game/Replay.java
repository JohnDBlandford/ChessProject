package Game;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import Board.Board;
import util.FileManager;

// This classes is similar to the Game class, but for replays.

public class Replay {

    private Scanner scanner = new Scanner(System.in);
    private List<Move> moveList;
    private Board board;
    private int currentIndex;

    // Constructor to find file via path from just name
    public Replay(String fileName) {
        File file = new File("src/saves/" + fileName);
        this.moveList = FileManager.getMoveList(file);
    }

    // Similar to the executeTurn function in Game
    public void replayGame() {

        board = new Board(8, 8);
        currentIndex = 0;
        boolean printBoardCheck = true;

        while (true) {

            if (printBoardCheck) {
                board.printBoard();
                System.out.println("This is turn " + (currentIndex + 1));
            } else {
                printBoardCheck = true;

            }

            System.out.println("Press \"n\" to make the next move and \"b\" to go back: ");
            String directionInput = scanner.next();

            if (directionInput.toLowerCase().equals("exit")) {

                System.out.println("Goodbye!");
                System.exit(0);

            }

            if (directionInput.equals("n")) {

                if (currentIndex >= moveList.size()) {
                    System.out.println("This is the end of the game");
                    currentIndex--;
                    printBoardCheck = false;
                    continue;
                }
                board.applyMove(moveList.get(currentIndex));
                currentIndex++;

            } else if (directionInput.equals("b")) {
                currentIndex--;

                if (currentIndex < 0) {
                    System.out.println("This is the start of the game, you cannot go back");
                    printBoardCheck = false;
                    currentIndex = 0;
                    continue;
                }
                board = new Board(8, 8);

                for (int i = 0; i < currentIndex; i++) {
                    board.applyMove(moveList.get(i));

                }

            } else {
                System.out.println("invalid command");
                printBoardCheck = false;
            }

        }

    }

    public void startReplay() {
        board = new Board(8, 8);
        currentIndex = 0;
    }

    public boolean moveForward() {

        if (currentIndex >= moveList.size()) {
            return false;
        }

        board.applyMove(moveList.get(currentIndex));
        currentIndex++;
        return true;
    }

    public boolean moveBackwards() {

        if (currentIndex <= 0) {
            return false;
        }

        currentIndex--;
        board = new Board(8, 8);
        for (int i = 0; i < currentIndex; i++) {
            board.applyMove(moveList.get(i));
        }
        return true;

    }

    public Board getBoard() {
        return board;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

}
