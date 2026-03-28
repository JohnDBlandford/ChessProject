package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Board.Board;
import Game.Game;
import Game.Move;

public class FileManager {

    public static void saveGame(List<Move> moveHistory, String fileName) {
        try {
            PrintWriter writer = new PrintWriter("saves/" + fileName);

            for (Move move : moveHistory) {
                writer.println(move.getFromRow() + "," + move.getFromCol() + "," +
                        move.getToRow() + "," + move.getToCol() + "," +
                        move.getMovedPiece().getPieceType() + "," +
                        move.getMovedPiece().getColor() + "," +
                        move.getIsCastle());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

    public static Game loadGame(File file) {

        Board board = new Board(8, 8);
        List<Move> moveHistory = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int fromRow = Integer.parseInt(parts[0]);
                int fromCol = Integer.parseInt(parts[1]);
                int toRow = Integer.parseInt(parts[2]);
                int toCol = Integer.parseInt(parts[3]);
                boolean isCastle = Boolean.parseBoolean(parts[6]);

                Move move = new Move(fromRow, fromCol, toRow, toCol, board.pieceAt(fromRow, fromCol),
                        board.getBoardData(), isCastle);
                moveHistory.add(move);
                board.applyMove(move);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return new Game(moveHistory, board);

    }

}
