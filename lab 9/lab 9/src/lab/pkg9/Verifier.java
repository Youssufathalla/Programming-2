package lab.pkg9;

public class Verifier {

    private final int[][] board;

    public Verifier(int[][] board) {
        this.board = board;
    }

    public String run(int mode) throws Exception {

        DuplicateResult result = new DuplicateResult();

        if (mode == 0) {

            for (int i = 0; i < 9; i++) {
                new RowChecker(board, result, i).run();
            }
            for (int j = 0; j < 9; j++) {
                new ColumnChecker(board, result, j).run();
            }
            for (int b = 0; b < 9; b++) {
                new BoxChecker(board, result, b).run();
            }
        } else if (mode == 3) {

            Thread tRows = new Thread(() -> {
                for (int i = 0; i < 9; i++) {
                    new RowChecker(board, result, i).run();
                }
            });

            Thread tCols = new Thread(() -> {
                for (int j = 0; j < 9; j++) {
                    new ColumnChecker(board, result, j).run();
                }
            });

            Thread tBoxes = new Thread(() -> {
                for (int b = 0; b < 9; b++) {
                    new BoxChecker(board, result, b).run();
                }
            });

            tRows.start();
            tCols.start();
            tBoxes.start();

            tRows.join();
            tCols.join();
            tBoxes.join();
        } else if (mode == 27) {

            Thread[] threads = new Thread[27];
            int idx = 0;

            for (int i = 0; i < 9; i++) {
                threads[idx++] = new Thread(
                        CheckerFactory.createChecker(0, board, result, i));
            }

            for (int j = 0; j < 9; j++) {
                threads[idx++] = new Thread(
                        CheckerFactory.createChecker(1, board, result, j));
            }

            for (int b = 0; b < 9; b++) {
                threads[idx++] = new Thread(
                        CheckerFactory.createChecker(2, board, result, b));
            }

            for (Thread t : threads) {
                t.start();
            }
            for (Thread t : threads) {
                t.join();
            }
        } else {
            return "Invalid mode. Use 0, 3, or 27.\n";
        }

        if (result.isValid()) {
            return "VALID\n";
        }

        StringBuilder sb = new StringBuilder("INVALID\n");
        for (String e : result.getErrors()) {
            sb.append(e).append("\n");
        }

        return sb.toString();
    }
}
