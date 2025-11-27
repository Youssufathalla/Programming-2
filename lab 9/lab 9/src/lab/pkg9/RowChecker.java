package lab.pkg9;

public class RowChecker extends AbstractChecker {

    public RowChecker(int[][] board, DuplicateResult result) {
        super(board, result);
    }

    @Override
    public void run() {

        for (int r = 0; r < 9; r++) {

            int[] values = new int[9];

            for (int c = 0; c < 9; c++) {
                values[c] = board[r][c];
            }

            int[] dup = checker.findDuplicatePositions(values);

            if (dup != null) {
                int duplicateValue = values[dup[0] - 1];

                result.addError(
                        "ROW " + (r + 1)
                        + " #" + duplicateValue
                        + " [" + dup[0] + "," + dup[1] + "]"
                );
            }
        }
    }
}
