import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(8, 8);
        String quitCheck = "";

        while (true) {
            if (!quitCheck.toLowerCase().equals("exit")) {
                board.printBoard();
                move(board);
                System.out.println("Type \"EXIT\" to quit. type anything else to continue");
                quitCheck = scanner.next();
            }

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
