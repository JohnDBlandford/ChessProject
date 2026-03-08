package Pieces;

import util.Color;
import util.PieceType;
import Game.Move;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);

    }

    @Override
    public boolean isLegalMove(Move move) {

        int fromRow = move.getFromRow();
        int fromCol = move.getFromCol();
        int toRow = move.getToRow();
        int toCol = move.getToCol();

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

    @Override
    public PieceType getPieceType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPieceType'");
    }

}
