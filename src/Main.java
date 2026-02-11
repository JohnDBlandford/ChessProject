import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(8, 8);
        board.printBoard();
        String quitCheck = "";
        System.out.println("Type \"EXIT\" to quit. type anything else to continue");
        quitCheck = scanner.nextLine();
        while (!quitCheck.toLowerCase().equals("exit")) {
            board.printBoard();
            move(board);
        }

    }

    private static void move(Board board) {
        String quitCheck = "";
        while (!quitCheck.toLowerCase().equals("exit")) {
            board.movePiece(scanner, board.getBoardData());
            System.out.println("Type \"EXIT\" to quit. type anything else to continue");
            quitCheck = scanner.nextLine();
        }

    }

}
