public abstract class Piece {

    Color color;

    Piece(Color color) {
        this.color = color;

    }

    public Color getColor() {
        return this.color;
    }

    public abstract boolean isLegalMove(int fromRow, int fromCol, int toRow, int toCol, Piece[][] boardData);

    protected abstract String getSymbol();

}