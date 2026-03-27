package Game;

import Pieces.*;

// This class stores relevant info about moves

public class Move {

    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private Piece movedPiece;
    private Piece capturedPiece;
    private Piece[][] board;
    private boolean isCastle;

    public Move(int fromRow, int fromCol, int toRow, int toCol, Piece piece, Piece[][] boardData, boolean isCastle) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.movedPiece = piece;
        this.board = boardData;
        this.isCastle = isCastle;

    }

    public int getFromCol() {
        return fromCol;
    }

    public int getFromRow() {
        return fromRow;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public int getToCol() {
        return toCol;
    }

    public int getToRow() {
        return toRow;
    }

    public boolean getIsCastle() {
        return isCastle;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;

    }

    public void setIsCastle(boolean bool) {
        this.isCastle = bool;

    }

}
