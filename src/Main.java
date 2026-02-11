import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(8, 8);

        board.printBoard();
        move(board);

    }

    // helper function to declutter main
    private static void move(Board board) {
        String exitCheck = "Null";
        while (!exitCheck.toLowerCase().equals("n")) {
            board.movePiece(scanner, board.getBoardData());
            System.out.println("Do you want to make another move (y) or exit (n)?");
            exitCheck = scanner.next();
        }

    }

}
