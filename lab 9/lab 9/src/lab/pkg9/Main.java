package lab.pkg9;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("Usage: java Sudoku.jar <csvfile>");
            return;
        }

        int[][] board = BoardReader.read(args[0]);
        Verifier verifier = new Verifier(board);

        System.out.println(verifier.run());
    }
}