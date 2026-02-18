
import java.util.Scanner;

import Board.Board;
import util.Color;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(8, 8);
        int turnCount = 0;
        Color playerTurn = Color.WHITE;
        board.printBoard();

        while (true) {
            turnCount += 1;
            move(board, turnCount, playerTurn);

        }

    }

    public static Color playerTurn(Color playerTurn, int turnCount) {

        if (turnCount % 2 != 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }

    }

    private static void move(Board board, int turnCount, Color playerTurn) {

        playerTurn = playerTurn(playerTurn, turnCount);

        System.out.println("This is turn " + turnCount);
        System.out.println("It is the " + playerTurn + " Player's turn");
        board.movePiece(scanner, board.getBoardData(), turnCount, playerTurn);
        board.printBoard();

    }

    public boolean isPlayerTurn(int turnCount) {

        return true;

    }

}
