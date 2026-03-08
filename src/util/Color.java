package util;

public enum Color {
    WHITE,
    BLACK;

    public Color opposite() {
        if (this == Color.WHITE) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }

    }
}
