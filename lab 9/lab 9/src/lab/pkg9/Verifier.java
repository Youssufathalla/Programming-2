package lab.pkg9;

public class Verifier {

    private final int[][] board;

    public Verifier(int[][] board) {
        this.board = board;
    }

    public String run(int mode) throws Exception {

        DuplicateResult result = new DuplicateResult();

        if (mode == 0) {

            AbstractChecker rowChecker = new RowChecker(board, result);
            AbstractChecker colChecker = new ColumnChecker(board, result);
            AbstractChecker boxChecker = new BoxChecker(board, result);

            rowChecker.run();
            colChecker.run();
            boxChecker.run();
        }

        else if (mode == 3) {

            Thread tRows = new Thread(new RowChecker(board, result));
            Thread tCols = new Thread(new ColumnChecker(board, result));
            Thread tBoxes = new Thread(new BoxChecker(board, result));

            tRows.start();
            tCols.start();
            tBoxes.start();

            tRows.join();
            tCols.join();
            tBoxes.join();
        }

        else if (mode == 27) {

            AbstractChecker[] checkers = new AbstractChecker[27];
            Thread[] threads = new Thread[27];
            int index = 0;

            for (int i = 0; i < 9; i++) {
                checkers[index] = CheckerFactory.createChecker(0, board, result);
                threads[index] = new Thread(checkers[index]);
                index++;
            }

            for (int j = 0; j < 9; j++) {
                checkers[index] = CheckerFactory.createChecker(1, board, result);
                threads[index] = new Thread(checkers[index]);
                index++;
            }

            for (int b = 0; b < 9; b++) {
                checkers[index] = CheckerFactory.createChecker(2, board, result);
                threads[index] = new Thread(checkers[index]);
                index++;
            }

            for (Thread t : threads) {
                t.start();
            }

            for (Thread t : threads) {
                t.join();
            }
        }

        else {
            return "Invalid mode. Use 0, 3, or 27.\n";
        }

        if (result.isValid()) {
            return "VALID\n";
        } else {
            StringBuilder sb = new StringBuilder("INVALID\n");
            for (String error : result.getErrors()) {
                sb.append(error).append("\n");
            }
            return sb.toString();
        }
    }
}
