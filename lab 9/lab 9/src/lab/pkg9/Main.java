package lab.pkg9;

public class Main {
    public static void main(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Usage: java -jar Sudoku.jar <csvfile> <mode>");
            return;
        }

        String file = args[0];
        int mode = Integer.parseInt(args[1]);

        int[][] board;

        try {
            board = BoardReader.read(file);
        } catch (Exception e) {
            System.out.println("INVALID INPUT");
            return;
        }

        Verifier v = new Verifier(board);
        System.out.println(v.run(mode));
    }
}
