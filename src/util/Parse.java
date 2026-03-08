package util;

public class Parse {

    public static int[] parseSquare(String square) {

        if (isValid(square)) {

            char fileFrom = square.charAt(0);
            char rankFrom = square.charAt(1);

            int colFrom = parseRow(fileFrom);
            int rowFrom = parseCol(rankFrom);

            return new int[] { rowFrom, colFrom };
        }

        else {
            throw new IllegalArgumentException("Invalid square: " + square);
        }

    }

    private static int parseRow(char fileFrom) {
        int movedCol = fileFrom - 'a';
        return movedCol;
    }

    private static int parseCol(char rankFrom) {
        int movedRow = 8 - (rankFrom - '0');
        return movedRow;

    }

    private static boolean isValid(String square) {

        if (square == null) {
            return false;
        }

        if (square.length() != 2) {
            return false;

        }

        if (square.charAt(0) > 'h' || square.charAt(0) < 'a') {
            return false;
        }
        if (square.charAt(1) > '8' || square.charAt(1) < '0') {
            return false;
        }

        return true;

    }

}
