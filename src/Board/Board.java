package Board;

import Game.Move;
import Pieces.*;
import util.Color;

public class Board {

    // Initialized Variables. All hardcoded to 8x8
    private int numRows = 8;
    private int numCols = 8;
    private Piece[][] boardData;
    // I stored that board stat as an 8x8 array of Piece objects. Spaces without
    // objects are considered null for future logic.

    // This Constructor sets a board size. Should probably hard code this instead.
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

    // This function prints the board in its current state from the boardData array
    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            int rank = 8 - row;
            System.out.print(rank + " ");

            for (int col = 0; col < 8; col++) {
                // Checks if there is a piece stored in the array. If not, prints either a black
                // or white square based on an alternating grid.
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

    public Piece pieceAt(int row, int col) {

        return boardData[row][col];

    }

    // takes a move object and updates the array
    public void applyMove(Move move) {

        int fromRow = move.getFromRow();
        int fromCol = move.getFromCol();
        int toRow = move.getToRow();
        int toCol = move.getToCol();
        move.setCapturedPiece(boardData[toRow][toCol]);

        boardData[toRow][toCol] = boardData[fromRow][fromCol];
        boardData[fromRow][fromCol] = null;

    }

    // Makes a copy of the existing board

    public Piece[][] makeCopy() {
        Piece[][] copy = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                copy[i][j] = boardData[i][j];
            }
        }

        return copy;

    }

}