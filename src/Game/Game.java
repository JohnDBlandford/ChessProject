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

    Board board;

    List<Move> moveHistory;

    Color currentTurn;

    int turnCount;

    Scanner scanner = new Scanner(System.in);

    public void startGame() {

        board = new Board(8, 8);
        moveHistory = new ArrayList<>();
        currentTurn = Color.WHITE;
        turnCount = 1;
        board.printBoard();

    }

    public void executeTurn() {

        while (true) {
            System.out.println("It is the " + currentTurn + " player's turn");
            System.out.println("Which Piece would you like to move");
            String movedSquare = scanner.nextLine();
            System.out.println("Where do you want to move that piece to?");
            String finalSquare = scanner.nextLine();

            try {
                int[] movedCords = Parse.parseSquare(movedSquare);
                int[] finalCords = Parse.parseSquare(finalSquare);

                int fromRow = movedCords[0];
                int fromCol = movedCords[1];
                int toRow = finalCords[0];
                int toCol = finalCords[1];
                Piece movedPiece = board.pieceAt(fromRow, fromCol);

                if (movedPiece == null) {
                    System.out.println("There is no piece there");
                    continue;
                }

                if (movedPiece.getColor() != currentTurn) {
                    System.out.println("That is not your piece");
                    continue;

                }

                Move move = new Move(fromRow, fromCol, toRow, toCol, movedPiece, board.getBoardData());

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

    public boolean isCheckmate() {

        Piece[][] tempBoard = board.makeCopy();

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

    public boolean isCheck(Piece[][] boardData) {

        int[] kingCords = findKing(boardData);
        if (kingCords[0] == -1) {
            return false;
        }

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

    private boolean correctType(int row, int col, PieceType type, Piece[][] boardData) {
        Piece piece = boardData[row][col];
        return piece != null && piece.getColor() != currentTurn && piece.getPieceType() == type;
    }

    private int[] findKing(Piece[][] boardData) {

        for (int i = 0; i < boardData.length; i++) {
            for (int j = 0; j < boardData[0].length; j++) {
                Piece piece = boardData[i][j];
                if (piece != null && piece.getPieceType() == PieceType.KING && piece.getColor() == currentTurn) {
                    return new int[] { i, j };
                }
            }
        }

        return new int[] { -1, -1 };
    }

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

}
