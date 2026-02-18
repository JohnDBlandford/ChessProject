package Game;

import java.util.Scanner;

import Game.Board;
import enums.Color;
import Game.Move;

public class Game {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(8, 8);
        int turnCount = 0;
        Color playerTurn = Color.WHITE;
        board.printBoard();

        while (true) {
            turnCount += 1;
            Move.move(board, turnCount, playerTurn);

        }

    }

    public static Color playerTurn(Color playerTurn, int turnCount) {

        if (turnCount % 2 != 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }

    }

    public boolean isPlayerTurn(int turnCount) {

        return true;

    }

}
