package Pieces;

import util.Color;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);

    }

    @Override
    public boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData) {

        if (fromRow == toRow) {
            return pieceInWayHorizontal(fromRow, fromCol, toCol, boardData);

        }

        if (fromCol == toCol) {
            return pieceInWayVertical(fromRow, fromCol, toRow, boardData);
        }

        if (Math.abs(fromRow - toRow) == Math.abs(toCol - fromCol)) {
            return pieceInWayDiagonal(fromRow, fromCol, toRow, toCol, boardData);
        }

        return false;

    }

    @Override
    public String getSymbol() {

        if (getColor() == Color.WHITE) {
            return "♛";
        } else {
            return "♕";
        }

    }

    private boolean pieceInWayDiagonal(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData) {

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

    private boolean pieceInWayVertical(int fromRow, int fromCol, int toRow, Piece[][] boardData) {

        int colNumber = fromCol;

        Piece[] col = new Piece[8];

        for (int i = 0; i < col.length; i++) {
            col[i] = boardData[i][colNumber];
        }

        int step = (toRow > fromRow) ? 1 : -1;

        for (int i = fromRow + step; i != toRow; i += step) {
            if (boardData[i][fromCol] != null) {
                return false;
            }
        }
        return true;

    }

    private boolean pieceInWayHorizontal(int fromRow, int fromCol, int toCol, Piece[][] boardData) {

        int rowNumber = fromRow;

        Piece[] row = new Piece[8];

        for (int i = 0; i < row.length; i++) {
            row[i] = boardData[rowNumber][i];
        }

        // up the board
        int step = (toCol > fromCol) ? 1 : -1;

        for (int i = fromCol + step; i != toCol; i += step) {
            if (boardData[fromRow][i] != null) {
                return false;
            }
        }
        return true;

    }

}
