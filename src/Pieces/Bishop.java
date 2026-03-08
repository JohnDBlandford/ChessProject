package Pieces;

import util.Color;
import util.PieceType;
import Game.Move;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);

    }

    @Override
    public boolean isLegalMove(Move move) {

        int fromRow = move.getFromRow();
        int fromCol = move.getFromCol();
        int toRow = move.getToRow();
        int toCol = move.getToCol();
        Piece[][] boardData = move.getBoard();

        // Cant move straight
        if (fromRow == toRow || fromCol == toCol) {

            return false;
        }

        // Diagonal must be 1 to 1
        if (Math.abs(fromRow - toRow) != Math.abs(fromCol - toCol)) {

            return false;
        }

        if (!pieceInWay(fromRow, fromCol, toRow, toCol, boardData)) {

            return false;
        }

        if (boardData[toRow][toCol] != null && getColor() == boardData[toRow][toCol].getColor()) {

            return false;
        }
        return true;

    }

    @Override
    public String getSymbol() {
        if (getColor() == Color.WHITE) {
            return "♝";
        } else {
            return "♗";
        }

    }

    private boolean pieceInWay(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData) {

        int rowDirection = 0;
        if (toRow > fromRow) {
            rowDirection = 1;
        } else {
            rowDirection = -1;
        }

        int colDirection = 0;
        if (toCol > fromCol) {
            colDirection = 1;
        } else {
            colDirection = -1;
        }

        int currentRow = fromRow + rowDirection;
        int currentCol = fromCol + colDirection;

        while (currentRow != toRow && currentCol != toCol) {
            if (boardData[currentRow][currentCol] != null) {
                return false;
            }

            currentRow += rowDirection;
            currentCol += colDirection;
        }
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }

}
