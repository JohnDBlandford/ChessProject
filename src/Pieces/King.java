package Pieces;

import enums.Color;

public class King extends Piece {

    public King(Color color) {
        super(color);

    }

    @Override
    public boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData) {
        if ((Math.abs(fromRow - toRow) > 1) || (Math.abs(fromCol - toCol) > 1)) {
            return false;
        }
        Piece piece = boardData[toRow][toCol];

        if (piece != null && piece.getColor() == getColor()) {
            System.out.println("You can't take your own pieces");
            return false;
        }

        return true;
    }

    @Override
    public String getSymbol() {

        if (getColor() == Color.WHITE) {
            return "♚";
        } else {
            return "♔";
        }

    }

}
