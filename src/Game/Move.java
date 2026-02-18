package Game;

import enums.Color;

public class Move {

    public void move(Board board, int turnCount, Color playerTurn) {

        playerTurn = Main.playerTurn(playerTurn, turnCount);

        System.out.println("This is turn " + turnCount);
        System.out.println("It is the " + playerTurn + " Player's turn");
        board.movePiece(scanner, board.getBoardData(), turnCount, playerTurn);
        board.printBoard();

    }

}
