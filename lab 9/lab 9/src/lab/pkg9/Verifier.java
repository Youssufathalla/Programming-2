package lab.pkg9;

public class Verifier {

    private final int[][] board;

    public Verifier(int[][] board) {
        this.board = board;
    }

    public String run() throws Exception {

        DuplicateResult result = new DuplicateResult();

       

            for (int i = 0; i < 9; i++) {
                new RowChecker(board, result, i).run();
            }
            for (int j = 0; j < 9; j++) {
                new ColumnChecker(board, result, j).run();
            }
            for (int b = 0; b < 9; b++) {
                new BoxChecker(board, result, b).run();
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
