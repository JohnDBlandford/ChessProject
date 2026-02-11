// Piece Class
// Every Piece has a symbol representing them
// Will later add individual piece behaviors but this works for now

public class Piece {

    private String symbol;

    public Piece(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

}