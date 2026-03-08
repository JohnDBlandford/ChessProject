package Game;

import Board.Board;
import Pieces.*;
import util.Color;
import util.Parse;
import util.PieceType;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Game {

    // The board is stored in a object
    Board board;

    // move history stored in a list. This is so that we know if its legal to
    // castle/en passant
    List<Move> moveHistory;

    // This tracks whos turn it is
    Color currentTurn;

    // Number of turns
    int turnCount;

    Scanner scanner = new Scanner(System.in);

    // Initilize the board and game
    public void startGame() {

        board = new Board(8, 8);
        moveHistory = new ArrayList<>();
        currentTurn = Color.WHITE;
        turnCount = 1;
        board.printBoard();

    }

    // Everything that happens in a turn is here.

    public void executeTurn() {

        // While true loop, broken out of with break/continue
        while (true) {

            // This gets the user input for moves
            System.out.println("It is the " + currentTurn + " player's turn");
            System.out.println("Which Piece would you like to move");
            String movedSquare = scanner.nextLine();
            System.out.println("Where do you want to move that piece to?");
            String finalSquare = scanner.nextLine();

            // Calls the parseSquare method to convert the user input into array
            // coordinates.
            try {
                int[] movedCords = Parse.parseSquare(movedSquare);
                int[] finalCords = Parse.parseSquare(finalSquare);

                // Parsed inputs are passed back as an array of two numbers
                int fromRow = movedCords[0];
                int fromCol = movedCords[1];
                int toRow = finalCords[0];
                int toCol = finalCords[1];
                Piece movedPiece = board.pieceAt(fromRow, fromCol);

                // Checks that there is a pice at the coordinates
                if (movedPiece == null) {
                    System.out.println("There is no piece there");
                    continue;
                }

                // Checks that the user is moving one of their own pieces
                if (movedPiece.getColor() != currentTurn) {
                    System.out.println("That is not your piece");
                    continue;

                }

                // Every move is stored as a "move" object to be saved in the move list
                Move move = new Move(fromRow, fromCol, toRow, toCol, movedPiece, board.getBoardData());

                // checks if the move is legal from the pieces individual logic, and then checks
                // if the king would be put in check
                if (movedPiece.isLegalMove(move)) {
                    Piece[][] tempBoard = board.makeCopy();
                    tempBoard[toRow][toCol] = movedPiece;
                    tempBoard[fromRow][fromCol] = null;
                    if (isCheck(tempBoard)) {
                        System.out.println("That move leaves your King in check");
                        continue;
                    }
                    board.applyMove(move);
                } else {
                    System.out.println("That move is not legal");
                    continue;
                }

                // Adds the move to the list
                moveHistory.add(move);

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Move");
                continue;
            }

            board.printBoard();

            switchTurn();

            break;

        }

    }

    // Helper function used to iterate the turn
    private void switchTurn() {

        this.turnCount += 1;
        if (turnCount % 2 == 0) {
            this.currentTurn = Color.BLACK;
        } else {
            this.currentTurn = Color.WHITE;
        }
    }

    public Color getCurrentTurn() {
        return this.currentTurn;
    }

    // This functions is used to find out if the king is in check in the current
    // boardstate.
    public boolean isCheck(Piece[][] boardData) {

        // Calls the findKing function to get the king's position
        int[] kingCords = findKing(boardData);
        if (kingCords[0] == -1) {
            return false;
        }

        // This functions works by creating a temporary piece in the kings position and
        // seeing if there is that type of piece on any of its available capture
        // squares.

        // Pawn
        Piece tempPawn = new Pawn(currentTurn.opposite());
        if (isAttackedBy(kingCords, tempPawn, PieceType.PAWN, boardData)) {
            return true;
        }

        // Knight
        Piece tempKnight = new Knight(currentTurn.opposite());
        if (isAttackedBy(kingCords, tempKnight, PieceType.KNIGHT, boardData)) {
            return true;
        }

        // Bishop
        Piece tempBishop = new Bishop(currentTurn.opposite());
        if (isAttackedBy(kingCords, tempBishop, PieceType.BISHOP, boardData)) {
            return true;
        }

        // Rook
        Piece tempRook = new Rook(currentTurn.opposite());
        if (isAttackedBy(kingCords, tempRook, PieceType.ROOK, boardData)) {
            return true;
        }

        // Queen
        Piece tempQueen = new Queen(currentTurn.opposite());
        if (isAttackedBy(kingCords, tempQueen, PieceType.QUEEN, boardData)) {
            return true;
        }

        return false;
    }

    // helper for the isCheck function. creates a move to every square, and if any
    // are both legal and land on a piece type that matches the logic, returns true
    private boolean isAttackedBy(int[] kingCords, Piece tempPiece, PieceType type, Piece[][] boardData) {
        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[0].length; j++) {
                Move tempMove = new Move(kingCords[0], kingCords[1], i, j, tempPiece, boardData);
                if (tempPiece.isLegalMove(tempMove) && correctType(i, j, type, boardData)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Function of PAIN AND SUFFERING.
    public boolean isCheckmate() {

        // Creates a temporary board that matches the real one
        Piece[][] tempBoard = board.makeCopy();

        // If the king is in check, this simulates every move a piece can make, and if
        // it can't find a legal move that gets the king out of check, it returns true.
        if (!isCheck(tempBoard)) {
            return false;
        }
        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[0].length; j++) {

                if (tempBoard[i][j] != null && tempBoard[i][j].getColor() == currentTurn) {

                    for (int k = 0; k < tempBoard.length; k++) {
                        for (int x = 0; x < tempBoard.length; x++) {

                            tempBoard = board.makeCopy();
                            Move tempMove = new Move(i, j, k, x, tempBoard[i][j], tempBoard);

                            if (tempBoard[i][j].isLegalMove(tempMove)) {
                                tempBoard[k][x] = tempBoard[i][j];
                                tempBoard[i][j] = null;
                                if (!isCheck(tempBoard)) {
                                    return false;
                                }
                            }

                        }
                    }

                }

            }
        }
        return true;
    }

    // Helper function for isAttackedBy
    private boolean correctType(int row, int col, PieceType type, Piece[][] boardData) {
        Piece piece = boardData[row][col];
        return piece != null && piece.getColor() != currentTurn && piece.getPieceType() == type;
    }

    // Finds the king
    private int[] findKing(Piece[][] boardData) {
        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[0].length; j++) {
                Piece piece = boardData[i][j];
                if (piece != null && piece.getPieceType() == PieceType.KING && piece.getColor() == currentTurn) {
                    return new int[] { i, j };
                }
            }
        }

        // This will never happen
        return new int[] { -1, -1 };
    }

}
