package Pieces;

import util.Color;

public abstract class Piece {

    private Color color;

    Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public abstract boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData);

    public abstract String getSymbol();

}