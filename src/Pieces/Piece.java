package Pieces;

import util.Color;
import util.PieceType;
import Game.Move;

public abstract class Piece {

    private Color color;

    Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public abstract boolean isLegalMove(Move move);

    public abstract String getSymbol();

    public abstract PieceType getPieceType();

}