package lab.pkg9;
public class BoxChecker extends AbstractChecker {

    public BoxChecker(int[][] board, DuplicateResult result) {
        super(board, result);
    }

    @Override
    public void run() {

        int boxNumber = 1;

        for (int br = 0; br < 3; br++) {
            for (int bc = 0; bc < 3; bc++) {

                int[] values = new int[9];
                int pos = 0;

                for (int r = br * 3; r < br * 3 + 3; r++) {
                    for (int c = bc * 3; c < bc * 3 + 3; c++) {
                        values[pos++] = board[r][c];
                    }
                }

                int[] dup = checker.findDuplicatePositions(values);

                if (dup != null) {
                    int duplicateValue = values[dup[0] - 1];

                    result.addError(
                        "BOX " + boxNumber +
                        " #" + duplicateValue +
                        " [" + dup[0] + "," + dup[1] + "]"
                    );
                }

                boxNumber++;
            }
        }
    }
}
