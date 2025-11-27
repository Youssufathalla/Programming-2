package lab.pkg9;


public class CheckerFactory {

    // type: 0 = row, 1 = col, 2 = box
    public static Checker createChecker(int type, int[][] board, int index) {
        switch (type) {
            case 0: return new RowChecker(board, index);
            case 1: return new ColumnChecker(board, index);
            case 2: return new BoxChecker(board, index);
            default: throw new IllegalArgumentException("Invalid checker type");
        }
    }
}
