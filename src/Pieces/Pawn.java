package Pieces;

import Game.Move;
import util.Color;
import util.PieceType;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isLegalMove(Move move) {

        int movedRow = move.getFromRow();
        int movedCol = move.getFromCol();
        int destRow = move.getToRow();
        int destCol = move.getToCol();
        Piece[][] boardData = move.getBoard();

        int direction = 0;
        if (getColor() == Color.WHITE) {
            direction = -1;
        } else {
            direction = 1;
        }

        // Forward one square
        if (movedCol == destCol &&
                destRow == movedRow + direction &&
                boardData[destRow][destCol] == null) {
            return true;
        }

        // Initial double move
        if (movedCol == destCol
                && ((getColor() == Color.WHITE && movedRow == 6) || (getColor() == Color.BLACK && movedRow == 1))
                && destRow == movedRow + 2 * direction && boardData[destRow][destCol] == null) {
            return true;
        }

        // Capture
        if (Math.abs(movedCol - destCol) == 1 && destRow == movedRow + direction && boardData[destRow][destCol] != null
                && boardData[destRow][destCol].getColor() != getColor()) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        if (getColor() == Color.WHITE) {
            return "♟";
        } else {
            return "♙";
        }

    }

    @Override
    public PieceType getPieceType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPieceType'");
    }

}
