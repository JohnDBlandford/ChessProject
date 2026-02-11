import java.util.Scanner;

public class Board {

    private int numRows = 8;
    private int numCols = 8;
    private Piece[][] boardData;

    public Board(int rows, int cols) {
        this.numRows = rows;
        this.numCols = cols;
        this.boardData = new Piece[this.numRows][this.numCols];
        initializeBoard();

    }

    private void initializeBoard() {
        boardData[7][4] = new Piece("K");
    }

    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            int rank = 8 - row;
            System.out.print(rank + " ");
            for (int col = 0; col < 8; col++) {
                Piece piece = this.boardData[row][col];
                if (piece != null) {
                    System.out.print(" " + piece.getSymbol() + " ");
                } else {
                    System.out.print((row + col) % 2 == 0 ? " □ " : " ■ ");
                }
            }
            System.out.println();
        }

        System.out.println("   a  b  c  d  e  f  g  h");
    }

    public Piece[][] getBoardData() {
        return boardData;
    }

    public void movePiece(Scanner scanner, Piece[][] boardData) {

        while (true) {

            System.out.print("Enter the square of the piece you want to move: ");
            String movedSquare = scanner.nextLine();

            Piece movedPiece = findPiece(movedSquare, boardData);

            if (movedPiece == null) {
                System.out.println("There is no piece there. Try again.");
                continue;
            }

            System.out.print("Enter the destination square (e.g. a3): ");
            String destSquare = scanner.nextLine();

            if (!moveTo(movedSquare, destSquare, boardData)) {
                System.out.println("That Square is not available. Try Again");
                continue;
            }
            printBoard();
            break;
        }
    }

    private Piece findPiece(String movedSquare, Piece[][] boardData) {

        if (movedSquare.length() < 2) {
            return null;
        }

        char file = movedSquare.charAt(0);
        char rank = movedSquare.charAt(1);

        int movedCol = file - 'a';
        int movedRow = numRows - (rank - '0');

        if (movedRow < 0 || movedRow >= numRows || movedCol < 0 || movedCol >= numCols) {
            return null;
        }

        if (boardData[movedRow][movedCol] == null) {
            return null;
        }

        return boardData[movedRow][movedCol];
    }

    private boolean moveTo(String movedSquare, String finalSquare, Piece[][] boardData) {

        if (finalSquare.length() < 2) {
            return false;
        }

        char fileFrom = movedSquare.charAt(0);
        char rankFrom = movedSquare.charAt(1);
        char fileTo = finalSquare.charAt(0);
        char rankTo = finalSquare.charAt(1);

        int movedCol = fileFrom - 'a';
        int movedRow = numRows - (rankFrom - '0');
        int destCol = fileTo - 'a';
        int destRow = numRows - (rankTo - '0');

        if (movedRow < 0 || movedRow >= numRows || movedCol < 0 || movedCol >= numCols || destRow < 0
                || destRow >= numRows || destCol < 0 || destCol >= numCols || boardData[movedRow][movedCol] == null) {
            return false;
        }

        boardData[destRow][destCol] = boardData[movedRow][movedCol];
        boardData[movedRow][movedCol] = null;
        return true;
    }
}