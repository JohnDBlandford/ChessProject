package Pieces;

import enums.Color;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);

    }

    @Override
    public boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData) {

        // Cant move straight
        if (fromRow == toRow || fromCol == toCol) {
            System.out.println("Bishop must move diagonally");
            return false;
        }

        // Diagonal must be 1 to 1
        if (Math.abs(fromRow - toRow) != Math.abs(fromCol - toCol)) {
            System.out.println("Bishop must move diagonally");
            return false;
        }

        if (!pieceInWay(fromRow, fromCol, toRow, toCol, boardData)) {
            System.out.println("There is a piece in the way");
            return false;
        }

        if (getColor() == boardData[toRow][toCol].getColor()) {
            System.out.println("You cannot take your own pieces");
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

}
