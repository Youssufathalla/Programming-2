package lab.pkg9;
public class ColumnChecker extends AbstractChecker {

    public ColumnChecker(int[][] board, DuplicateResult result) {
        super(board, result);
    }

    @Override
    public void run() {

        for (int c = 0; c < 9; c++) {

            int[] values = new int[9];

            for (int r = 0; r < 9; r++) {
                values[r] = board[r][c];
            }

            int[] dup = checker.findDuplicatePositions(values);

            if (dup != null) {
                int duplicateValue = values[dup[0] - 1];

                result.addError(
                    "COL " + (c + 1) +
                    " #" + duplicateValue +
                    " [" + dup[0] + "," + dup[1] + "]"
                );
            }
        }
    }
}
