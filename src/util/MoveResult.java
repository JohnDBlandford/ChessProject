package util;

public enum MoveResult {

    SUCCESS,
    WRONG_TURN,
    NO_PIECE,
    ILLEGAL_MOVE,
    LEAVES_KING_IN_CHECK,
    CASTLE_ILLEGAL
}
