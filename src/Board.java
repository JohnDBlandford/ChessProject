// This is where the magic happens
// This class contain all data about the board, what pieces are on it, and where
//the movePiece function is also here. Will probably move it to Main/Dedicated game manger class. but for now Im lazy

import java.util.Scanner;

public class Board {

    // Initialized Variables
    private int numRows = 8;
    private int numCols = 8;
    private Piece[][] boardData;

    // Above is the big money piece. I store that board stat as an 8x8 array of
    // Piece objects. spaces without objects are considered null for future logic.
    // This kinda scares me. I logically understand it, but did not expect it to
    // work like this Constructor. Sets a board size. I don't forsee needing a non
    // 8x8 board, but
    // allowing customizability felt like best practices, and didn't add much
    // difficulty.
    public Board(int rows, int cols) {
        this.numRows = rows;
        this.numCols = cols;
        this.boardData = new Piece[this.numRows][this.numCols];
        initializeBoard();

    }

    // This function sets the pieces in their initial places. One side only until I
    // figure out how i want to differentiate black and white
    private void initializeBoard() {

        for (int i = 0; i < 8; i++) {
            boardData[6][i] = new Piece("P");
        }

        boardData[7][0] = new Piece("R");
        boardData[7][1] = new Piece("N");
        boardData[7][2] = new Piece("B");
        boardData[7][3] = new Piece("Q");
        boardData[7][4] = new Piece("K");
        boardData[7][5] = new Piece("B");
        boardData[7][6] = new Piece("N");
        boardData[7][7] = new Piece("R");

    }

    // This function prints the board in its current state from the boardData array
    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            int rank = 8 - row;
            System.out.print(rank + " ");

            for (int col = 0; col < 8; col++) {
                // Checks if there is a piece stored in the array. If not, prints either a black
                // or white square based on an alternating grid.
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

    // simple getter
    public Piece[][] getBoardData() {
        return boardData;
    }

    // big money function. Handles all input and output of moving the pieces. calls
    // findPiece to determine where the piece currently is, and then moveTo to check
    // if that square is legal, and if it is, update the pieces location in the
    // boardData array
    public void movePiece(Scanner scanner, Piece[][] boardData) {

        // runs in an endless look until the user enters a valid input. will add escape
        // sequnce later
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

    // helper function to find the piece the user wants to move
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

    // helper function to verify if the the user inputs are legal and, if they are,
    // actually move the piece.
    private boolean moveTo(String movedSquare, String finalSquare, Piece[][] boardData) {

        // stops array out of bounds errors
        if (finalSquare.length() < 2) {
            return false;
        }

        // converts rank and file (user input) into row and column. Will probably change
        // the boardData to use rank and file at some point, but this works for now.
        char fileFrom = movedSquare.charAt(0);
        char rankFrom = movedSquare.charAt(1);
        char fileTo = finalSquare.charAt(0);
        char rankTo = finalSquare.charAt(1);

        int movedCol = fileFrom - 'a';
        int movedRow = numRows - (rankFrom - '0');
        int destCol = fileTo - 'a';
        int destRow = numRows - (rankTo - '0');

        // Giant mess, but this has all the possible invalid inputs a user could enter
        // that i could think of. May split it up for readability
        if (movedRow < 0 || movedRow >= numRows || movedCol < 0 || movedCol >= numCols || destRow < 0
                || destRow >= numRows || destCol < 0 || destCol >= numCols || boardData[movedRow][movedCol] == null) {
            return false;
        }

        //
        boardData[destRow][destCol] = boardData[movedRow][movedCol];
        boardData[movedRow][movedCol] = null;
        return true;
    }
}