
public class Pawn extends Piece {

    Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isLegalMove(int movedRow, int movedCol, int destRow, int destCol, Piece[][] boardData) {

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
        if (movedCol == destCol &&
                ((getColor() == Color.WHITE && movedRow == 6) ||
                        (getColor() == Color.BLACK && movedRow == 1))
                &&
                destRow == movedRow + 2 * direction &&
                boardData[destRow][destCol] == null) {
            return true;
        }

        // Capture
        if (Math.abs(movedCol - destCol) == 1 &&
                destRow == movedRow + direction &&
                boardData[destRow][destCol] != null &&
                boardData[destRow][destCol].getColor() != getColor()) {
            return true;
        }

        return false;
    }

    @Override
    public String getSymbol() {
        if (getColor() == Color.WHITE) {
            return "♙";
        } else {
            return "♟";
        }

    }

}
