package lab10;

public class Main {

    public static void main(String[] args) throws Exception {

        

        int[][] board = BoardReader.read(args[0]);
        Verifier verifier = new Verifier(board);

        System.out.println(verifier.run());
    }
}