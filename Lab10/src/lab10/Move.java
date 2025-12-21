package lab10;

public class Move {
    public int row;
    public int col;
    public int value;
    public int previousValue;

    public Move(int row, int col, int value, int previousValue) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.previousValue = previousValue;
    }

    @Override
    public String toString() {
        return row + "," + col + "," + value + "," + previousValue;
    }

    public static Move fromString(String line) {
        String[] parts = line.split(",");
        return new Move(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3])
        );
    }
}
