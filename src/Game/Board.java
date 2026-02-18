package Game;

import java.util.Scanner;

import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Rook;
import enums.Color;
import Pieces.Bishop;
import Pieces.Knight;
import Pieces.Queen;
import Pieces.King;

public class Board {

    private int numRows = 8;
    private int numCols = 8;
    private Piece[][] boardData;

    public Board(int rows, int cols) {
        this.numRows = rows;
        this.numCols = cols;
        this.boardData = new Piece[this.numRows][this.numCols];
        initializeBoard();

    }

    private void initializeBoard() {

        for (int i = 0; i < 8; i++) {
            boardData[1][i] = new Pawn(Color.BLACK);
        }
        for (int i = 0; i < 8; i++) {
            boardData[6][i] = new Pawn(Color.WHITE);
        }

        boardData[7][0] = new Rook(Color.WHITE);
        boardData[7][7] = new Rook(Color.WHITE);
        boardData[0][0] = new Rook(Color.BLACK);
        boardData[0][7] = new Rook(Color.BLACK);
        boardData[7][5] = new Bishop(Color.WHITE);
        boardData[7][2] = new Bishop(Color.WHITE);
        boardData[0][5] = new Bishop(Color.BLACK);
        boardData[0][2] = new Bishop(Color.BLACK);
        boardData[7][3] = new Queen(Color.WHITE);
        boardData[0][3] = new Queen(Color.BLACK);
        boardData[7][1] = new Knight(Color.WHITE);
        boardData[7][6] = new Knight(Color.WHITE);
        boardData[0][1] = new Knight(Color.BLACK);
        boardData[0][6] = new Knight(Color.BLACK);
        boardData[7][4] = new King(Color.WHITE);
        boardData[0][4] = new King(Color.BLACK);

    }

    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            int rank = 8 - row;
            System.out.print(rank + " ");

            for (int col = 0; col < 8; col++) {

                Piece piece = this.boardData[row][col];
                if (piece != null) {
                    System.out.print(" " + piece.getSymbol() + " ");
                } else {
                    System.out.print((row + col) % 2 == 0 ? " ■ " : " □ ");
                }
            }
            System.out.println();
        }

        System.out.println("   a  b  c  d  e  f  g  h");
    }

    public Piece[][] getBoardData() {
        return boardData;
    }

    public void movePiece(Scanner scanner, Piece[][] boardData, int turnCount, Color playerTurn) {

        while (true) {

            System.out.print("Enter the square of the piece you want to move: ");
            String movedSquare = scanner.nextLine();
            Piece movedPiece = findPiece(movedSquare, boardData);

            if (movedPiece == null) {
                System.out.println("There is no piece there. Try again.");
                continue;
            }

            if (movedPiece.getColor() != playerTurn) {
                System.out.println("You cannot move an opponent's piece");
                continue;
            }

            System.out.print("Where do you want to move that piece?: ");
            String destSquare = scanner.nextLine();

            if (!moveTo(movedSquare, destSquare, boardData)) {
                System.out.println("That Square is not available. Try Again");
                continue;
            }

            break;
        }
    }

    private Piece findPiece(String movedSquare, Piece[][] boardData) {

        if (movedSquare.length() < 2) {
            return null;
        }

        char file = movedSquare.charAt(0);
        char rank = movedSquare.charAt(1);

        int movedCol = file - 'a';
        int movedRow = numRows - (rank - '0');

        if (movedRow < 0 || movedRow >= numRows || movedCol < 0 || movedCol >= numCols) {
            return null;
        }

        if (boardData[movedRow][movedCol] == null) {
            return null;
        }

        return boardData[movedRow][movedCol];
    }

    // helper function to verify if the the user inputs are legal and, if they are,
    // actually move the piece.
    private boolean moveTo(String movedSquare, String finalSquare, Piece[][] boardData) {

        if (finalSquare.length() < 2) {
            return false;
        }

        char fileFrom = movedSquare.charAt(0);
        char rankFrom = movedSquare.charAt(1);
        char fileTo = finalSquare.charAt(0);
        char rankTo = finalSquare.charAt(1);

        int movedCol = fileFrom - 'a';
        int movedRow = numRows - (rankFrom - '0');
        int destCol = fileTo - 'a';
        int destRow = numRows - (rankTo - '0');

        // Giant mess, but this has all the possible invalid inputs a user could enter
        // that I could think of. May split it up for readability
        if (movedRow < 0 || movedRow >= numRows || movedCol < 0 || movedCol >= numCols || destRow < 0
                || destRow >= numRows || destCol < 0 || destCol >= numCols || boardData[movedRow][movedCol] == null) {
            return false;
        }

        Piece movedPiece = boardData[movedRow][movedCol];
        if (!movedPiece.isLegalMove(movedRow, movedCol, destRow, destCol, boardData)) {
            return false;
        }

        // Update the boardData array.
        boardData[destRow][destCol] = boardData[movedRow][movedCol];
        boardData[movedRow][movedCol] = null;
        return true;
    }

    public static boolean isOccupied(Piece[][] boardData, int destRow, int destCol) {

        if (boardData[destRow][destCol] != null) {
            return true;
        } else {
            return false;
        }

    }
}