package lab.pkg9;

import java.util.ArrayList;

public class Verifier {

    private int[][] board;

    public Verifier(int[][] board) {
        this.board = board;
    }

    public String run(int mode) throws Exception {
        ArrayList<String> finalErrors = new ArrayList<>();
        boolean finalValid = true;

        if (mode == 0) {

            for (int i = 0; i < 9; i++) {
                RowChecker w = new RowChecker(board, i);
                w.run();
                if (!w.isValid()) {
                    finalValid = false;
                }
                finalErrors.addAll(w.getErrors());
            }

            for (int j = 0; j < 9; j++) {
                ColumnChecker w = new ColumnChecker(board, j);
                w.run();
                if (!w.isValid()) {
                    finalValid = false;
                }
                finalErrors.addAll(w.getErrors());
            }

            for (int b = 0; b < 9; b++) {
                BoxChecker w = new BoxChecker(board, b);
                w.run();
                if (!w.isValid()) {
                    finalValid = false;
                }
                finalErrors.addAll(w.getErrors());
            }
        } else if (mode == 3) {

            Thread tRows = new Thread(() -> {
                for (int i = 0; i < 9; i++) {
                    RowChecker w = new RowChecker(board, i);
                    w.run();
                    if (!w.isValid()) {
                        finalValid = false;
                    }
                    synchronized (finalErrors) {
                        finalErrors.addAll(w.getErrors());
                    }
                }
            });

            Thread tCols = new Thread(() -> {
                for (int j = 0; j < 9; j++) {
                    ColumnChecker w = new ColumnChecker(board, j);
                    w.run();
                    if (!w.isValid()) {
                        finalValid = false;
                    }
                    synchronized (finalErrors) {
                        finalErrors.addAll(w.getErrors());
                    }
                }
            });

            Thread tBoxes = new Thread(() -> {
                for (int b = 0; b < 9; b++) {
                    BoxChecker w = new BoxChecker(board, b);
                    w.run();
                    if (!w.isValid()) {
                        finalValid = false;
                    }
                    synchronized (finalErrors) {
                        finalErrors.addAll(w.getErrors());
                    }
                }
            });

            tRows.start();
            tCols.start();
            tBoxes.start();

            tRows.join();
            tCols.join();
            tBoxes.join();
        } else if (mode == 27) {

            Checker[] checkers = new Checker[27];
            Thread[] threads = new Thread[27];
            int idx = 0;

            for (int i = 0; i < 9; i++) {
                checkers[idx++] = new RowChecker(board, i);
            }
            for (int j = 0; j < 9; j++) {
                checkers[idx++] = new ColumnChecker(board, j);
            }
            for (int b = 0; b < 9; b++) {
                checkers[idx++] = new BoxChecker(board, b);
            }

            for (int i = 0; i < 27; i++) {
                threads[i] = new Thread(checkers[i]);
            }

            for (Thread t : threads) {
                t.start();
            }
            for (Thread t : threads) {
                t.join();
            }

            for (Checker c : checkers) {
                if (!c.isValid()) {
                    finalValid = false;
                }
                finalErrors.addAll(c.getErrors());
            }
        } else {
            return "Invalid mode. Use 0, 3, or 27.\n";
        }

        if (finalValid) {
            return "VALID\n";
        }

        StringBuilder sb = new StringBuilder("INVALID\n");
        for (String e : finalErrors) {
            sb.append(e).append("\n");
        }
        return sb.toString();
    }
}
