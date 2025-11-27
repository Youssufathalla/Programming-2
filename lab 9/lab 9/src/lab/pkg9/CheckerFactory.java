package lab.pkg9;

public class CheckerFactory {

    public static AbstractChecker createChecker(int type, int[][] board, DuplicateResult result) {

        switch (type) {
            case 0:
                return new RowChecker(board, result);

            case 1:
                return new ColumnChecker(board, result);

            case 2:
                return new BoxChecker(board, result);

            default:
                throw new IllegalArgumentException("Invalid checker type");
        }
    }
}
