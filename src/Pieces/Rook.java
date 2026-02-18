package Pieces;

import util.Color;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData) {

        if (boardData[toRow][toCol] != null && boardData[toRow][toCol].getColor() == getColor()) {
            System.out.println("You cannot capture your own pieces");
            return false;
        }

        String moveDirection = moveDirection(fromRow, fromCol, toRow, toCol);
        // vertical moves
        if (moveDirection.equals("vertical")) {
            boolean check = pieceInWayVertical(fromRow, fromCol, toRow, boardData);
            if (check == false) {
                System.out.println("There is a piece in the way!");
                return false;
            }
        }
        if (moveDirection.equals("horizontal")) {
            boolean check = pieceInWayHorizontal(fromRow, fromCol, toCol, boardData);
            if (check == false) {
                System.out.println("There is a piece in the way!");
                return false;
            }
        }

        if (moveDirection.equals("false")) {
            return false;
        }

        return true;

    }

    @Override
    public String getSymbol() {

        if (getColor() == Color.WHITE) {
            return "♜";
        } else {
            return "♖";
        }

    }

    private String moveDirection(int fromRow, int fromCol, int toRow, int toCol) {

        if (fromRow - toRow != 0 && fromCol - toCol == 0) {
            return "vertical";
        }
        if (fromCol - toCol != 0 && fromRow - toRow == 0) {
            return "horizontal";
        } else {
            return "false";
        }

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
