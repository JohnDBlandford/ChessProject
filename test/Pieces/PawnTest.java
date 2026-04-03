package Pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Board.Board;
import Game.Move;
import util.Color;

public class PawnTest {

    @Test
    void testIsLegalMoveBase() {

        Board board = new Board(8, 8);

        Piece pawn = new Pawn(Color.WHITE);
        Move move = new Move(6, 0, 5, 0, pawn, board.getBoardData(), false);

        assertEquals(true, pawn.isLegalMove(move));

    }

}
