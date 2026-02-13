
public class Pawn extends Piece {

    private Color color;

    Pawn(Color color) {
        super(color);
    }

    public boolean isLegalMove(int destCol, int destRow, int movedCol, int movedRow, Piece[][] boardData) {

        if (this.color == Color.WHITE) {
            if (destRow > movedRow) {

                return false;

            }

            if (movedRow != 6 && Math.abs(destRow - destRow) > 1) {

                return false;
            }

            if (movedRow == 6 && Math.abs(destRow - destRow) > 2) {

                return false;
            }

            if (Math.abs(movedCol - destCol) > 0 && Board.isOccupied(boardData) == false) {

                return false;
            }

            if (Math.abs(movedCol - destCol) > 0 && Board.isOccupied(boardData) == true && getColor() == Color.WHITE) {

                return false;
            }

            if (Math.abs(movedCol - destCol) > 1 && Board.isOccupied(boardData) == true) {

                return false;
            }

            return true;

        }

        if (this.color == Color.BLACK) {
            if (destRow < movedRow) {
                return false;
            }

            if (movedRow != 6 && Math.abs(destRow - destRow) > 1) {
                return false;
            }

            if (movedRow == 6 && Math.abs(destRow - destRow) > 2) {
                return false;
            }

            if (Math.abs(movedCol - destCol) > 0 && Board.isOccupied(boardData) == false) {
                return false;
            }

            if (Math.abs(movedCol - destCol) > 0 && Board.isOccupied(boardData) == true && getColor() == Color.WHITE) {
                return false;
            }

            if (Math.abs(movedCol - destCol) > 1 && Board.isOccupied(boardData) == true) {
                return false;
            }

            return true;

        }

        else {
            return true;
        }

    }

    public Color getColor() {
        return this.color;
    }

    public String getSymbol() {

        if (this.color == Color.WHITE) {
            return "♙";
        } else {
            return "♟";
        }

    }

}
