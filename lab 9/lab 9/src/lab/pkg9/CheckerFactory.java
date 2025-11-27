package lab.pkg9;

public class CheckerFactory {

    public static AbstractChecker createChecker(int type, int[][] board, DuplicateResult result, int index) {
        return switch (type) {
            case 0 -> new RowChecker(board, result, index);
            case 1 -> new ColumnChecker(board, result, index);
            case 2 -> new BoxChecker(board, result, index);
            default -> throw new IllegalArgumentException("Invalid checker type");
        };
    }
}
