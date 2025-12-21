package lab.pkg9;

public class Game {

    private int[][] board;
    private String difficulty;

    public Game(int[][] board, String difficulty) {
        this.board = board;
        this.difficulty = difficulty;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Difficulty: ").append(difficulty).append("\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
                if (j < 8) sb.append(", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
