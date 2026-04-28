import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

import Board.Board;
import Game.Game;
import util.FileManager;

public class CheckmateTest {

    @Test
    void CheckmateTest1() {

        File file = new File("checkmate.txt");

        Board board = new Board(8, 8);

        Game game = new Game(FileManager.getMoveList(file), board);

        game.isCheckmate();

        assertEquals(true, game.isCheckmate());

    }

}
