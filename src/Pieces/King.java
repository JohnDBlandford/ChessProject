package Pieces;

import util.Color;
import util.PieceType;
import Game.Move;

public class King extends Piece {

    PieceType KING;

    public King(Color color) {
        super(color);

    }

    @Override
    public boolean isLegalMove(Move move) {

        int fromRow = move.getFromRow();
        int fromCol = move.getFromCol();
        int toRow = move.getToRow();
        int toCol = move.getToCol();
        Piece[][] boardData = move.getBoard();

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

    public PieceType getPieceType() {
        return PieceType.KING;
    }

}
