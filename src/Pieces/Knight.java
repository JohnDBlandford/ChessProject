package Pieces;

import util.Color;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);

    }

    @Override
    public boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData) {

        if (Math.abs(fromRow - toRow) == 2) {
            if (Math.abs(fromCol - toCol) == 1) {
                return true;
            }
        }
        if (Math.abs(fromCol - toCol) == 2) {
            if (Math.abs(fromRow - toRow) == 1) {
                return true;
            }
        }
        if (Math.abs(fromRow - toRow) == 1) {
            if (Math.abs(fromCol - toCol) == 2) {
                return true;
            }
        }
        if (Math.abs(fromCol - toCol) == 1) {
            if (Math.abs(fromRow - toRow) == 2) {
                return true;
            }
        }

        return false;

    }

    @Override
    public String getSymbol() {

        if (getColor() == Color.WHITE) {
            return "♞";
        } else {
            return "♘";
        }

    }

}
